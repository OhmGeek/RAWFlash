# Render Properties that can be adjusted:


```json
{
  "exposure": "EXPOSURE VALUE: 0.0 to any upper value.",
  "histogram-equalization": "True/False. In practice, if present then it will be turned on.",
  "adams-wb": "True/False, if white balance control on",
  "adams-wb-red": "Red gain, as double.",
  "adams-wb-green": "Green Gain, as double",
  "adams-wb-blue": "Blue Gain, as double",
  "mean-blur": "kernel size as integer here: Odd number, 3 or more.",
  "gaussian-blur": "Gaussian blur kernel size: odd number, 3 or more.",
  "adams-gamma": "true/false",
  "adams-gamma-gamma": "gamma as a double"
}
```


## TODO:
- gaussian blur (code there, needs to be hooked in)
- gamma correction (both sides need to be written)
- Haze Removal

### Gamma Correction
This is the basic code for generating Gamma Correction look up table.

```java
// Create the gamma correction lookup table
 private static int[] gamma_LUT(double gamma_new) {
     int[] gamma_LUT = new int[256];
     for (int i = 0; i < gamma_LUT.length; i++) {
         gamma_LUT[i] = (int) (255 * (Math.pow((double) i / (double) 255, gamma_new)));
     }
     return gamma_LUT;
 }
```
Then from here, we go through and only for RED, GREEN, and BLUE, we lookup the image value, and apply the one in the look up table. Alpha is left alone.

To speed this up, rather than custom loops, use Java's LookupUp (with a defined Lookup Table based on the code sample above).
