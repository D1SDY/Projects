cmake_minimum_required(VERSION 3.15)
project(striaant)

set(CMAKE_CXX_STANDARD 14)

add_executable(striaant main.cpp autocorrect.hpp autocorrect.cpp)