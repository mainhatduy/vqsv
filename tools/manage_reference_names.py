#!/usr/bin/env python3
"""Manage and format tools/reference-names.json without breaking single-line compact array format.

Usage:
  python3 tools/manage_reference_names.py format
  python3 tools/manage_reference_names.py add-field <owner> <name> <desc> <newName>
  python3 tools/manage_reference_names.py add-method <owner> <name> <desc> <newName>
  python3 tools/manage_reference_names.py add-param <owner> <name> <desc> <p1> [p2 ...]
"""

import argparse
import json
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent
NAMES_FILE = ROOT / "tools/reference-names.json"


def load_mapping():
    with open(NAMES_FILE, "r", encoding="utf-8") as f:
        return json.load(f)


def save_mapping_compact(data):
    """Write JSON with classes indented, and each field/method/parameter entry on a single line."""
    lines = []
    lines.append("{")
    desc = data.get("description", "")
    lines.append(f'  "description": {json.dumps(desc, ensure_ascii=False)},')

    # Classes
    lines.append('  "classes": {')
    classes = data.get("classes", {})
    class_items = list(classes.items())
    for i, (old_cls, new_cls) in enumerate(class_items):
        comma = "," if i < len(class_items) - 1 else ""
        lines.append(f'    "{old_cls}": "{new_cls}"{comma}')
    lines.append("  },")

    # Arrays (fields, methods, parameters)
    sections = [
        ("fields", data.get("fields", [])),
        ("methods", data.get("methods", [])),
        ("parameters", data.get("parameters", [])),
    ]

    for sec_idx, (sec_name, sec_items) in enumerate(sections):
        lines.append(f'  "{sec_name}": [')
        for i, item in enumerate(sec_items):
            comma = "," if i < len(sec_items) - 1 else ""
            compact_json = json.dumps(item, ensure_ascii=False)
            lines.append(f"    {compact_json}{comma}")
        is_last = (sec_idx == len(sections) - 1) and ("notes" not in data)
        lines.append("  ]" if is_last else "  ],")

    if "notes" in data:
        lines.append('  "notes": [')
        notes = data["notes"]
        for i, note in enumerate(notes):
            comma = "," if i < len(notes) - 1 else ""
            lines.append(f"    {json.dumps(note, ensure_ascii=False)}{comma}")
        lines.append("  ]")

    lines.append("}\n")
    NAMES_FILE.write_text("\n".join(lines), encoding="utf-8")


def check_duplicates(data):
    seen = set()
    duplicates = []
    for kind in ("fields", "methods", "parameters"):
        for item in data.get(kind, []):
            key = (kind, item[0], item[1], item[2])
            if key in seen:
                duplicates.append(key)
            seen.add(key)
    return duplicates


def main():
    parser = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    subparsers = parser.add_subparsers(dest="command", required=True)

    # format
    subparsers.add_parser("format", help="Lint and reformat reference-names.json in compact 1-line array style")

    # add-field
    p_field = subparsers.add_parser("add-field", help="Add or update a field mapping")
    p_field.add_argument("owner", help="Owner class (e.g. j or game/b)")
    p_field.add_argument("name", help="Original field name (e.g. a)")
    p_field.add_argument("desc", help="JVM descriptor (e.g. I or [I)")
    p_field.add_argument("new_name", help="Readable new name")

    # add-method
    p_method = subparsers.add_parser("add-method", help="Add or update a method mapping")
    p_method.add_argument("owner", help="Owner class (e.g. j or game/b)")
    p_method.add_argument("name", help="Original method name (e.g. a)")
    p_method.add_argument("desc", help="JVM descriptor (e.g. (II)V)")
    p_method.add_argument("new_name", help="Readable new name")

    # add-param
    p_param = subparsers.add_parser("add-param", help="Add or update method parameters")
    p_param.add_argument("owner", help="Owner class")
    p_param.add_argument("name", help="Original method name")
    p_param.add_argument("desc", help="JVM descriptor")
    p_param.add_argument("params", nargs="+", help="Parameter names in order")

    args = parser.parse_args()
    data = load_mapping()

    if args.command == "format":
        dups = check_duplicates(data)
        if dups:
            print(f"Warning: Found {len(dups)} duplicate symbol(s):")
            for d in dups:
                print(f"  {d}")
        save_mapping_compact(data)
        print(f"Formatted {NAMES_FILE} successfully ({len(data.get('fields', []))} fields, {len(data.get('methods', []))} methods, {len(data.get('parameters', []))} parameters).")

    elif args.command == "add-field":
        entry = [args.owner, args.name, args.desc, args.new_name]
        fields = data.setdefault("fields", [])
        # replace if exists, else append
        for i, f in enumerate(fields):
            if f[:3] == entry[:3]:
                fields[i] = entry
                print(f"Updated field: {entry}")
                break
        else:
            fields.append(entry)
            print(f"Added field: {entry}")
        save_mapping_compact(data)

    elif args.command == "add-method":
        entry = [args.owner, args.name, args.desc, args.new_name]
        methods = data.setdefault("methods", [])
        for i, m in enumerate(methods):
            if m[:3] == entry[:3]:
                methods[i] = entry
                print(f"Updated method: {entry}")
                break
        else:
            methods.append(entry)
            print(f"Added method: {entry}")
        save_mapping_compact(data)

    elif args.command == "add-param":
        entry = [args.owner, args.name, args.desc, args.params]
        params = data.setdefault("parameters", [])
        for i, p in enumerate(params):
            if p[:3] == entry[:3]:
                params[i] = entry
                print(f"Updated parameters: {entry}")
                break
        else:
            params.append(entry)
            print(f"Added parameters: {entry}")
        save_mapping_compact(data)


if __name__ == "__main__":
    main()
