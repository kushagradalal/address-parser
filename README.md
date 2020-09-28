# Address Parser (US)
A utility to seperate different parts of an address string into its constituent fields.

## Features
- Extract info from an address string into street, po box, city, state, state code, zip code.
- Supports 60000+ city aliases.
- Only 512 KB JAR build.
- Works offline.

## Usage
```
Address address = AddressParser.getAddress("your address string");
```