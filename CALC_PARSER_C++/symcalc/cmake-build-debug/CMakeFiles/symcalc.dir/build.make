# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.15

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /Applications/CLion.app/Contents/bin/cmake/mac/bin/cmake

# The command to remove a file.
RM = /Applications/CLion.app/Contents/bin/cmake/mac/bin/cmake -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /Volumes/MacDisk/c++/PJC/symcalc

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /Volumes/MacDisk/c++/PJC/symcalc/cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/symcalc.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/symcalc.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/symcalc.dir/flags.make

CMakeFiles/symcalc.dir/tokenizer.cpp.o: CMakeFiles/symcalc.dir/flags.make
CMakeFiles/symcalc.dir/tokenizer.cpp.o: ../tokenizer.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/Volumes/MacDisk/c++/PJC/symcalc/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/symcalc.dir/tokenizer.cpp.o"
	/Library/Developer/CommandLineTools/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/symcalc.dir/tokenizer.cpp.o -c /Volumes/MacDisk/c++/PJC/symcalc/tokenizer.cpp

CMakeFiles/symcalc.dir/tokenizer.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/symcalc.dir/tokenizer.cpp.i"
	/Library/Developer/CommandLineTools/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /Volumes/MacDisk/c++/PJC/symcalc/tokenizer.cpp > CMakeFiles/symcalc.dir/tokenizer.cpp.i

CMakeFiles/symcalc.dir/tokenizer.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/symcalc.dir/tokenizer.cpp.s"
	/Library/Developer/CommandLineTools/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /Volumes/MacDisk/c++/PJC/symcalc/tokenizer.cpp -o CMakeFiles/symcalc.dir/tokenizer.cpp.s

CMakeFiles/symcalc.dir/cmdline.cpp.o: CMakeFiles/symcalc.dir/flags.make
CMakeFiles/symcalc.dir/cmdline.cpp.o: ../cmdline.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/Volumes/MacDisk/c++/PJC/symcalc/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Building CXX object CMakeFiles/symcalc.dir/cmdline.cpp.o"
	/Library/Developer/CommandLineTools/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/symcalc.dir/cmdline.cpp.o -c /Volumes/MacDisk/c++/PJC/symcalc/cmdline.cpp

CMakeFiles/symcalc.dir/cmdline.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/symcalc.dir/cmdline.cpp.i"
	/Library/Developer/CommandLineTools/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /Volumes/MacDisk/c++/PJC/symcalc/cmdline.cpp > CMakeFiles/symcalc.dir/cmdline.cpp.i

CMakeFiles/symcalc.dir/cmdline.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/symcalc.dir/cmdline.cpp.s"
	/Library/Developer/CommandLineTools/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /Volumes/MacDisk/c++/PJC/symcalc/cmdline.cpp -o CMakeFiles/symcalc.dir/cmdline.cpp.s

CMakeFiles/symcalc.dir/expr.cpp.o: CMakeFiles/symcalc.dir/flags.make
CMakeFiles/symcalc.dir/expr.cpp.o: ../expr.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/Volumes/MacDisk/c++/PJC/symcalc/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_3) "Building CXX object CMakeFiles/symcalc.dir/expr.cpp.o"
	/Library/Developer/CommandLineTools/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/symcalc.dir/expr.cpp.o -c /Volumes/MacDisk/c++/PJC/symcalc/expr.cpp

CMakeFiles/symcalc.dir/expr.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/symcalc.dir/expr.cpp.i"
	/Library/Developer/CommandLineTools/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /Volumes/MacDisk/c++/PJC/symcalc/expr.cpp > CMakeFiles/symcalc.dir/expr.cpp.i

CMakeFiles/symcalc.dir/expr.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/symcalc.dir/expr.cpp.s"
	/Library/Developer/CommandLineTools/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /Volumes/MacDisk/c++/PJC/symcalc/expr.cpp -o CMakeFiles/symcalc.dir/expr.cpp.s

CMakeFiles/symcalc.dir/expr_impl.cpp.o: CMakeFiles/symcalc.dir/flags.make
CMakeFiles/symcalc.dir/expr_impl.cpp.o: ../expr_impl.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/Volumes/MacDisk/c++/PJC/symcalc/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_4) "Building CXX object CMakeFiles/symcalc.dir/expr_impl.cpp.o"
	/Library/Developer/CommandLineTools/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/symcalc.dir/expr_impl.cpp.o -c /Volumes/MacDisk/c++/PJC/symcalc/expr_impl.cpp

CMakeFiles/symcalc.dir/expr_impl.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/symcalc.dir/expr_impl.cpp.i"
	/Library/Developer/CommandLineTools/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /Volumes/MacDisk/c++/PJC/symcalc/expr_impl.cpp > CMakeFiles/symcalc.dir/expr_impl.cpp.i

CMakeFiles/symcalc.dir/expr_impl.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/symcalc.dir/expr_impl.cpp.s"
	/Library/Developer/CommandLineTools/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /Volumes/MacDisk/c++/PJC/symcalc/expr_impl.cpp -o CMakeFiles/symcalc.dir/expr_impl.cpp.s

CMakeFiles/symcalc.dir/app.cpp.o: CMakeFiles/symcalc.dir/flags.make
CMakeFiles/symcalc.dir/app.cpp.o: ../app.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/Volumes/MacDisk/c++/PJC/symcalc/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_5) "Building CXX object CMakeFiles/symcalc.dir/app.cpp.o"
	/Library/Developer/CommandLineTools/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/symcalc.dir/app.cpp.o -c /Volumes/MacDisk/c++/PJC/symcalc/app.cpp

CMakeFiles/symcalc.dir/app.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/symcalc.dir/app.cpp.i"
	/Library/Developer/CommandLineTools/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /Volumes/MacDisk/c++/PJC/symcalc/app.cpp > CMakeFiles/symcalc.dir/app.cpp.i

CMakeFiles/symcalc.dir/app.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/symcalc.dir/app.cpp.s"
	/Library/Developer/CommandLineTools/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /Volumes/MacDisk/c++/PJC/symcalc/app.cpp -o CMakeFiles/symcalc.dir/app.cpp.s

CMakeFiles/symcalc.dir/main.cpp.o: CMakeFiles/symcalc.dir/flags.make
CMakeFiles/symcalc.dir/main.cpp.o: ../main.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/Volumes/MacDisk/c++/PJC/symcalc/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_6) "Building CXX object CMakeFiles/symcalc.dir/main.cpp.o"
	/Library/Developer/CommandLineTools/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/symcalc.dir/main.cpp.o -c /Volumes/MacDisk/c++/PJC/symcalc/main.cpp

CMakeFiles/symcalc.dir/main.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/symcalc.dir/main.cpp.i"
	/Library/Developer/CommandLineTools/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /Volumes/MacDisk/c++/PJC/symcalc/main.cpp > CMakeFiles/symcalc.dir/main.cpp.i

CMakeFiles/symcalc.dir/main.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/symcalc.dir/main.cpp.s"
	/Library/Developer/CommandLineTools/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /Volumes/MacDisk/c++/PJC/symcalc/main.cpp -o CMakeFiles/symcalc.dir/main.cpp.s

# Object files for target symcalc
symcalc_OBJECTS = \
"CMakeFiles/symcalc.dir/tokenizer.cpp.o" \
"CMakeFiles/symcalc.dir/cmdline.cpp.o" \
"CMakeFiles/symcalc.dir/expr.cpp.o" \
"CMakeFiles/symcalc.dir/expr_impl.cpp.o" \
"CMakeFiles/symcalc.dir/app.cpp.o" \
"CMakeFiles/symcalc.dir/main.cpp.o"

# External object files for target symcalc
symcalc_EXTERNAL_OBJECTS =

symcalc: CMakeFiles/symcalc.dir/tokenizer.cpp.o
symcalc: CMakeFiles/symcalc.dir/cmdline.cpp.o
symcalc: CMakeFiles/symcalc.dir/expr.cpp.o
symcalc: CMakeFiles/symcalc.dir/expr_impl.cpp.o
symcalc: CMakeFiles/symcalc.dir/app.cpp.o
symcalc: CMakeFiles/symcalc.dir/main.cpp.o
symcalc: CMakeFiles/symcalc.dir/build.make
symcalc: CMakeFiles/symcalc.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/Volumes/MacDisk/c++/PJC/symcalc/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_7) "Linking CXX executable symcalc"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/symcalc.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/symcalc.dir/build: symcalc

.PHONY : CMakeFiles/symcalc.dir/build

CMakeFiles/symcalc.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/symcalc.dir/cmake_clean.cmake
.PHONY : CMakeFiles/symcalc.dir/clean

CMakeFiles/symcalc.dir/depend:
	cd /Volumes/MacDisk/c++/PJC/symcalc/cmake-build-debug && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /Volumes/MacDisk/c++/PJC/symcalc /Volumes/MacDisk/c++/PJC/symcalc /Volumes/MacDisk/c++/PJC/symcalc/cmake-build-debug /Volumes/MacDisk/c++/PJC/symcalc/cmake-build-debug /Volumes/MacDisk/c++/PJC/symcalc/cmake-build-debug/CMakeFiles/symcalc.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/symcalc.dir/depend

