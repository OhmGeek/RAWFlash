cmake_minimum_required(VERSION 3.7)
project(RenderServer)

set(CMAKE_CXX_STANDARD 11)

set(SOURCE_FILES src/main.cpp)

##### Load ECM #####
find_package(ECM REQUIRED NO_MODULE)
set(CMAKE_MODULE_PATH ${ECM_MODULE_PATH})





add_executable(RenderServer ${SOURCE_FILES})