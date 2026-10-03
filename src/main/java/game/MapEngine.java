package game;

import java.io.DataInputStream;
import java.io.InputStream;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class MapEngine {
   private static MapEngine instance;
   private static final int[] transformLookupTable = new int[]{0, 5, 3, 6, 2, 4, 1, 7};
   private int viewportWidth = -1;
   private int viewportHeight = -1;
   private short mapColumns = -1;
   private short mapRows = -1;
   private short tileWidth = -1;
   private short tileHeight = -1;
   private int mapId = -1;
   private byte tilesetId = -1;
   private byte previousTilesetId = -1;
   private Image[] tileImages = null;
   public int cameraX;
   public int cameraY;
   private int lastCameraX;
   private int lastCameraY;
   public int mapPixelWidth;
   public int mapPixelHeight;
   private int startTileCol;
   private int startTileRow;
   private int endTileCol;
   private int endTileRow;
   private int visibleTileCols;
   private int visibleTileRows;
   private byte layerCount;
   private short[][] tileDefinitions;
   private short[][][] layerData;
   private byte[] layerTypes = null;
   private static Image bufferImage;
   private static Graphics bufferGraphics;
   private boolean needsFullRedraw = true;

   public static MapEngine getInstance() {
      if (instance == null) {
         instance = new MapEngine();
      }

      return instance;
   }

   public MapEngine() {
      this.viewportHeight = BaseScreen.getScreenHeight();
      this.viewportWidth = BaseScreen.getScreenWidth();
   }

   public final void release() {
      if (this.tileImages != null) {
         for (int var1 = 0; var1 < this.tileImages.length; var1++) {
            this.tileImages[var1] = null;
         }

         this.tileImages = null;
      }

      for (int var2 = 0; var2 < GameDatabase.tilesetImageTable[this.tilesetId].length; var2++) {
         ImageCache.evictImage(GameDatabase.tilesetImageTable[this.tilesetId][var2]);
      }

      bufferImage = null;
      bufferGraphics = null;
      MapEngine var3 = this;
      this.layerData = null;
      var3.layerTypes = null;
      var3.tileDefinitions = null;
   }

   public final void loadMap(int var1) {
      this.mapId = var1;
      MapEngine var12 = this;

      try {
         var12.getClass();
         InputStream var13 = ResourceStream.openResource("/data/map/map_" + var12.mapId + ".mid");
         DataInputStream var14;
         byte var3 = (var14 = new DataInputStream(var13)).readByte();
         var12.previousTilesetId = var12.tilesetId;
         var12.tilesetId = var14.readByte();
         var12.loadTileset();
         if (var3 == 1) {
            var12.mapColumns = var14.readByte();
         } else {
            var12.mapColumns = var14.readShort();
         }

         if (var3 == 1) {
            var12.mapRows = var14.readByte();
         } else {
            var12.mapRows = var14.readShort();
         }

         var12.tileWidth = var14.readByte();
         var12.tileHeight = var12.tileWidth;
         var12.mapPixelWidth = var12.mapColumns * var12.tileWidth;
         var12.mapPixelHeight = var12.mapRows * var12.tileHeight;
         var12.layerCount = var14.readByte();
         var12.layerTypes = new byte[var12.layerCount];
         var12.layerData = new short[var12.layerCount][][];

         for (int var4 = 0; var4 < var12.layerCount; var4++) {
            byte var5 = var14.readByte();
            var12.layerTypes[var4] = var14.readByte();
            short var6 = var14.readShort();
            if (var12.layerTypes[var5] != 0 && var12.layerTypes[var5] != 1) {
               var12.layerData[var5] = new short[var6][4];
            } else {
               var12.layerData[var5] = new short[var12.mapColumns][var12.mapRows];

               for (int var7 = 0; var7 < var12.mapColumns; var7++) {
                  for (int var8 = 0; var8 < var12.mapRows; var8++) {
                     var12.layerData[var5][var7][var8] = -1;
                  }
               }
            }

            for (int var15 = 0; var15 < var6; var15++) {
               short var9;
               short var16;
               if (var3 == 1) {
                  var16 = var14.readByte();
                  var9 = var14.readByte();
               } else {
                  var16 = var14.readShort();
                  var9 = var14.readShort();
               }

               short var10 = var14.readShort();
               if (var12.layerTypes[var4] == 1) {
                  var12.layerData[var4][var16][var9] = var10;
               } else if (var12.layerTypes[var5] == 0) {
                  var12.layerData[var4][var16][var9] = (short)(var10 & 4095);
               } else {
                  var12.layerData[var4][var15][1] = var16;
                  var12.layerData[var4][var15][2] = var9;
                  var12.layerData[var4][var15][0] = (short)(var10 & 4095);
                  var12.layerData[var4][var15][3] = (short)((var10 & 28672) >> 12);
               }
            }
         }

         var14.close();
      } catch (Exception var11) {
         Object var2 = null;
         var11.printStackTrace();
      }

      if (bufferImage == null) {
         bufferGraphics = (bufferImage = Image.createImage(this.viewportWidth, this.viewportHeight)).getGraphics();
      }

      this.needsFullRedraw = true;
   }

   private void loadTileset() {
      if (this.tileImages != null) {
         for (int var1 = 0; var1 < this.tileImages.length; var1++) {
            for (int var2 = 0; var2 < GameDatabase.tilesetImageTable[this.tilesetId].length; var2++) {
               if (GameDatabase.tilesetImageTable[this.previousTilesetId][var1] == GameDatabase.tilesetImageTable[this.tilesetId][var2]) {
                  ImageCache.releaseImage(GameDatabase.tilesetImageTable[this.previousTilesetId][var1]);
                  this.tileImages[var1] = null;
                  break;
               }
            }

            if (this.tileImages[var1] != null) {
               ImageCache.evictImage(GameDatabase.tilesetImageTable[this.previousTilesetId][var1]);
               this.tileImages[var1] = null;
            }
         }

         this.tileImages = null;
      }

      this.tileImages = new Image[GameDatabase.tilesetImageTable[this.tilesetId].length];

      for (int var6 = 0; var6 < this.tileImages.length; var6++) {
         this.tileImages[var6] = ImageCache.getImage(GameDatabase.tilesetImageTable[this.tilesetId][var6]);
      }

      try {
         "".getClass();
         InputStream var7 = ResourceStream.openResource("/data/mod/mod_" + this.tilesetId + ".mid");
         DataInputStream var8;
         short var3 = (var8 = new DataInputStream(var7)).readShort();
         this.tileDefinitions = new short[var3][5];

         for (int var4 = 0; var4 < var3; var4++) {
            this.tileDefinitions[var4][0] = var8.readByte();
            this.tileDefinitions[var4][1] = var8.readShort();
            this.tileDefinitions[var4][2] = var8.readShort();
            this.tileDefinitions[var4][3] = var8.readShort();
            this.tileDefinitions[var4][4] = var8.readShort();
         }

         var8.close();
         var7.close();
      } catch (Exception var5) {
         var5.printStackTrace();
      }
   }

   public final void updateVisibleBounds() {
      if (this.tileWidth != 0) {
         this.startTileRow = this.cameraY / this.tileHeight;
         this.startTileCol = this.cameraX / this.tileWidth;
         this.endTileRow = (this.cameraY + this.viewportHeight) / this.tileHeight;
         if ((this.cameraY + this.viewportHeight) % this.tileHeight != 0) {
            this.endTileRow++;
         }

         if (this.endTileRow > this.mapRows) {
            this.endTileRow = this.mapRows;
         }

         this.endTileCol = (this.cameraX + this.viewportWidth) / this.tileWidth;
         if ((this.cameraX + this.viewportWidth) % this.tileWidth != 0) {
            this.endTileCol++;
         }

         if (this.endTileCol > this.mapColumns) {
            this.endTileCol = this.mapColumns;
         }

         this.visibleTileRows = this.viewportHeight / this.tileHeight + 1;
         this.visibleTileCols = this.viewportWidth / this.tileWidth + 1;
         if (this.startTileRow + this.visibleTileRows >= this.mapRows) {
            this.visibleTileRows = this.mapRows - 1 - this.startTileRow;
         }

         if (this.visibleTileCols + this.startTileCol >= this.mapColumns) {
            this.visibleTileCols = this.mapColumns - 1 - this.startTileCol;
         }
      }
   }

   private void renderTileRegion(Graphics var1, int var2, int var3, int var4, int var5, int var6) {
      WorldManager.a();
      WorldManager.a(var1, var3, var4, var5, var6);
      switch (this.layerTypes[var2]) {
         case 0:
            this.renderBasicGridRegion(var1, var2, var3, var4, var5, var6);
            return;
         case 1:
            this.renderTransformedGridRegion(var1, var2, var3, var4, var5, var6);
      }
   }

   public final void renderLayer(Graphics var1, int var2, int var3) {
      switch (this.layerTypes[var2]) {
         case 0:
         case 1:
            var3 = var2;
            Graphics var11 = var1;
            MapEngine var9 = this;
            if (this.needsFullRedraw) {
               int var14 = var9.cameraX / var9.tileWidth < 0 ? 0 : var9.cameraX / var9.tileWidth;
               int var5 = var9.cameraY / var9.tileHeight < 0 ? 0 : var9.cameraY / var9.tileHeight;
               int var6 = (var9.cameraX + var9.viewportWidth) / var9.tileWidth + 1 > var9.mapColumns
                  ? var9.mapColumns
                  : (var9.cameraX + var9.viewportWidth) / var9.tileWidth + 1;
               int var7 = (var9.cameraY + var9.viewportHeight) / var9.tileHeight + 1 > var9.mapRows
                  ? var9.mapRows
                  : (var9.cameraY + var9.viewportHeight) / var9.tileHeight + 1;
               WorldManager.a();
               WorldManager.a(bufferGraphics, 0, 0, var9.viewportWidth, var9.viewportHeight);
               switch (var9.layerTypes[var3]) {
                  case 0:
                     var9.renderBasicGridRegion(bufferGraphics, var3, var14, var5, var6, var7);
                     break;
                  case 1:
                     var9.renderTransformedGridRegion(bufferGraphics, var3, var14, var5, var6, var7);
               }

               var9.needsFullRedraw = false;
            } else if (var9.lastCameraX != var9.cameraX || var9.lastCameraY != var9.cameraY) {
               int var15 = 0;
               int var18 = 0;
               if (var9.cameraX > var9.lastCameraX) {
                  var15 = var9.lastCameraX - var9.cameraX;
               } else if (var9.cameraX < var9.lastCameraX) {
                  var15 = var9.lastCameraX - var9.cameraX;
               }

               if (var9.cameraY > var9.lastCameraY) {
                  var18 = var9.lastCameraY - var9.cameraY;
               } else if (var9.cameraY < var9.lastCameraY) {
                  var18 = var9.lastCameraY - var9.cameraY;
               }

               bufferGraphics.copyArea(0, 0, var9.viewportWidth, var9.viewportHeight, var15, var18, 20);
               if (var9.cameraX > var9.lastCameraX) {
                  int var19 = (var9.lastCameraX + var9.viewportWidth) / var9.tileWidth;
                  var9.renderTileRegion(bufferGraphics, var3, var19, var9.startTileRow, var9.endTileCol, var9.endTileRow);
               } else if (var9.cameraX < var9.lastCameraX) {
                  var15 = var9.lastCameraX / var9.tileWidth + 1;
                  var9.renderTileRegion(bufferGraphics, var3, var9.startTileCol, var9.startTileRow, var15, var9.endTileRow);
               }

               if (var9.cameraY > var9.lastCameraY) {
                  int var20 = (var9.lastCameraY + var9.viewportHeight) / var9.tileHeight;
                  var9.renderTileRegion(bufferGraphics, var3, var9.startTileCol, var20, var9.endTileCol, var9.endTileRow);
               } else if (var9.cameraY < var9.lastCameraY) {
                  var15 = var9.lastCameraY / var9.tileHeight + 1;
                  var9.renderTileRegion(bufferGraphics, var3, var9.startTileCol, var9.startTileRow, var9.endTileCol, var15);
               }
            }

            var11.drawImage(bufferImage, 0, 0, 20);
            var9.lastCameraX = var9.cameraX;
            var9.lastCameraY = var9.cameraY;
            return;
         case 2:
         case 3:
         case 4:
            var3 = var2;
            Graphics var10 = var1;
            MapEngine var8 = this;

            for (int var4 = 0; var4 < var8.layerData[var3].length; var4++) {
               if (var8.layerData[var3][var4][2] >= 0
                  && EngineUtils.a(
                     var8.layerData[var3][var4][1],
                     var8.layerData[var3][var4][2],
                     var8.tileDefinitions[var8.layerData[var3][var4][0]][3],
                     var8.tileDefinitions[var8.layerData[var3][var4][0]][4],
                     var8.startTileCol,
                     var8.startTileRow,
                     var8.visibleTileCols + 1,
                     var8.visibleTileRows + 1,
                     var8.layerData[var3][var4][3]
                  )) {
                  var10.drawRegion(
                     var8.tileImages[var8.tileDefinitions[var8.layerData[var3][var4][0]][0]],
                     var8.tileDefinitions[var8.layerData[var3][var4][0]][1],
                     var8.tileDefinitions[var8.layerData[var3][var4][0]][2],
                     var8.tileDefinitions[var8.layerData[var3][var4][0]][3],
                     var8.tileDefinitions[var8.layerData[var3][var4][0]][4],
                     transformLookupTable[var8.layerData[var3][var4][3]],
                     (var8.layerData[var3][var4][1] - var8.startTileCol) * var8.tileWidth - var8.cameraX % var8.tileWidth,
                     (var8.layerData[var3][var4][2] - var8.startTileRow) * var8.tileHeight - var8.cameraY % var8.tileHeight,
                     20
                  );
               }
            }
      }
   }

   private void renderBasicGridRegion(Graphics var1, int var2, int var3, int var4, int var5, int var6) {
      while (var3 < var5) {
         for (int var7 = var4; var7 < var6; var7++) {
            short var8;
            if ((var8 = this.layerData[var2][var3][var7]) != -1) {
               var1.drawRegion(
                  this.tileImages[0],
                  this.tileDefinitions[var8][1],
                  this.tileDefinitions[var8][2],
                  this.tileDefinitions[var8][3],
                  this.tileDefinitions[var8][4],
                  0,
                  var3 * this.tileWidth - this.cameraX,
                  var7 * this.tileHeight - this.cameraY,
                  20
               );
            }
         }

         var3++;
      }
   }

   private void renderTransformedGridRegion(Graphics var1, int var2, int var3, int var4, int var5, int var6) {
      while (var3 < var5) {
         for (int var7 = var4; var7 < var6; var7++) {
            short var8;
            if ((var8 = this.layerData[var2][var3][var7]) != -1) {
               short var9 = (short)(var8 & 4095);
               var8 = (short)transformLookupTable[(var8 & 28672) >> 12];
               var1.drawRegion(
                  this.tileImages[this.tileDefinitions[var9][0]],
                  this.tileDefinitions[var9][1],
                  this.tileDefinitions[var9][2],
                  this.tileDefinitions[var9][3],
                  this.tileDefinitions[var9][4],
                  var8,
                  var3 * this.tileWidth - this.cameraX,
                  var7 * this.tileHeight - this.cameraY,
                  20
               );
            }
         }

         var3++;
      }
   }

   public final void centerCamera(int var1, int var2) {
      this.cameraX = var1 - this.viewportWidth / 2;
      this.cameraY = var2 - this.viewportHeight / 2;
      if (this.cameraX + this.viewportWidth >= this.mapColumns * this.tileWidth) {
         this.cameraX = this.mapColumns * this.tileWidth - this.viewportWidth;
      }

      if (this.cameraX <= 0) {
         this.cameraX = 0;
      }

      if (this.cameraY + this.viewportHeight >= this.mapRows * this.tileHeight) {
         this.cameraY = this.mapRows * this.tileHeight - this.viewportHeight;
      }

      if (this.cameraY <= 0) {
         this.cameraY = 0;
      }
   }

   public final byte getCollisionTile(int var1, int var2) {
      if (this.layerData != null && this.layerData[0] != null) {
         int var3 = var1 / this.tileWidth;
         int var4 = var2 / this.tileHeight;
         if (this.isOutOfBounds(var1, var2)) {
            return 1;
         }

         if (var4 < 0) {
            var4 = 0;
         }

         if (var3 < 0) {
            var3 = 0;
         }

         if (var3 > this.mapColumns) {
            var3 = this.mapColumns;
         }

         if (var4 > this.mapRows) {
            var4 = this.mapRows;
         }

         return (byte)this.layerData[0][var3][var4];
      } else {
         return -1;
      }
   }

   public final boolean isOutOfBounds(int var1, int var2) {
      return var1 <= 0 || var1 >= this.mapPixelWidth || var2 <= 0 || var2 >= this.mapPixelHeight;
   }

   static {
      int[] var10000 = new int[]{0, 270, 180, 90, 8192, 8462, 8372, 8282};
   }
}
