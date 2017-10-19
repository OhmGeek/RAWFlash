# RAWFlash
A cloud based RAW photo editor.


## Structure:
### docs folder
This contains all documentation written as part of the project.

The project log contains regular updates regarding project updates. Supervisor meetings will also be summarised here.

### client
This contains the code for the web client, that interfaces with the server.

### rawflash
This is the main body of the system, the render server.

## Dependencies
- Java 8
- Apache Maven (for dependency management within rawflash)
- npm/nodejs (for the web client)
- dcraw executable

## FAQs
### Why?
Why not?

The main reason is to introduce RAW image editing to people who don't have high-end systems.

Furthermore, if we disregard the web client, we can utilize the server side within Content Management Systems to edit images in one swoop, without further preprocessing using a current off the shelf tool like Lightroom or RawTherapee.

### dcraw? 

dcraw is a vital dependency, because it's the only way we can physically view RAw image files without implementing our own RAW loader (and therefore condensing the project to focus on one particular format). Therefore, we can export to tiff, and then tiff can be read using Java, so we can do our advanced image processing using this.

Other possible decisions included libraw, which was C++ based and had very little/no documentation, so making the project far harder than it needed to be. 
