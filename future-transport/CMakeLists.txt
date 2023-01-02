#
# Created by Alexander J. on 02.01.2023.
#

cmake_minimum_required(VERSION 3.24)
project(future_transport VERSION 1.0)

set(CMAKE_CXX_STANDARD 17)
set(SOURCE base.cpp)

find_package(JNI REQUIRED)

if (JNI_FOUND)
    message(STATUS "could found jni: ${JNI_VERSION}.")
    include_directories(${JNI_INCLUDE_DIRS})
endif ()

add_executable(future_transport base.cpp base.h)

if(JNI_FOUND)
    message("cmake script successfully ran!")
    message("built by @HarmanTBK")
else()
    message("please contact contact@cadium.org")
endif()