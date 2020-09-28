# Address Parser (US)
A utility to seperate different parts of an address string into its constituent fields.

## Features
- Extract info from an address string into street, po box, city, state, state code, zip code.
- Supports 60000+ city aliases.
- Only 512 KB JAR build.
- Works offline.

## Usage
- Run <b>JAR.bat</b> and look for <b>address-parser-1.0.jar</b> in target folder
- Add <b>address-parser-1.0.jar</b> to your project classpath
```
Address address = AddressParser.getAddress("your address string");
```