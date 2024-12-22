#!/bin/pyhon3

import csv

# readable
# version
# protocol_version
# protocol_type
# release_type
# data_version
# resource_pack_format
# data_pack_format
# notes

def encode_protocol_version(version: str) -> str:
    if "0x" in version:
        return hex(int(version, 16))
    else:
        return hex(int(version))

def encode_protocol_type(ptype: str) -> str:
    match ptype:
        case "OLD_PRE_NETTY":
            return "0b01"
        case "PRE_NETTY":
            return "0b10"
        case "NETTY":
            return "0b11"
        case _:
            return "0b00"

def encode_release_type(rtype: str) -> str:
    match rtype:
        case "SNAPSHOT":
            return "0b001"
        case "EXP_SNAPSHOT":
            return "0b010"
        case "PRE_RELEASE":
            return "0b011"
        case "RELEASE_CANDIDATE":
            return "0b100"
        case "RELEASE":
            return "0b101"
        case _:
            return "0b000"

def encode_meta_version(num: str) -> str:
    return hex(int(num))

def convert_to_binary(lst: list[str]) -> (str, list[str]):
    key: str = lst[1]

    out = []
    out.append(encode_protocol_version(lst[2]))
    out.append(encode_protocol_type(lst[3]))
    out.append(encode_release_type(lst[4]))
    out.append(encode_meta_version(lst[5]))
    out.append(encode_meta_version(lst[6]))
    out.append(encode_meta_version(lst[7]))

    return (key, out)

def convert_to_output_string(key: str, o: list[str]) -> str:
    return "dataCache.put(\"" + key + "\", new Integer[] {" + ", ".join(o) + "});"

if __name__ == "__main__":
    with open("./version_meta.csv", "r") as f:
        reader = csv.reader(f)
        first: bool = False
        for row in reader:
            if not first:
                first = True
                continue
            if "header-" in row[0]:
                continue

            key, out = convert_to_binary(row)
            line = convert_to_output_string(key, out)
            print(line)

