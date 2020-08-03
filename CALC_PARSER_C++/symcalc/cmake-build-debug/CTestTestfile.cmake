# CMake generated Testfile for 
# Source directory: /Volumes/MacDisk/c++/PJC/symcalc
# Build directory: /Volumes/MacDisk/c++/PJC/symcalc/cmake-build-debug
# 
# This file includes the relevant testing commands required for 
# testing this directory and lists subdirectories to be tested as well.
add_test(direct "/Volumes/MacDisk/c++/PJC/symcalc/cmake-build-debug/tests-direct")
set_tests_properties(direct PROPERTIES  _BACKTRACE_TRIPLES "/Volumes/MacDisk/c++/PJC/symcalc/CMakeLists.txt;88;add_test;/Volumes/MacDisk/c++/PJC/symcalc/CMakeLists.txt;0;")
add_test(runner-direct "/Volumes/MacDisk/c++/PJC/symcalc/cmake-build-debug/tests-runner-direct")
set_tests_properties(runner-direct PROPERTIES  _BACKTRACE_TRIPLES "/Volumes/MacDisk/c++/PJC/symcalc/CMakeLists.txt;89;add_test;/Volumes/MacDisk/c++/PJC/symcalc/CMakeLists.txt;0;")
add_test(runner-subprocess "/Volumes/MacDisk/c++/PJC/symcalc/cmake-build-debug/tests-runner-subprocess")
set_tests_properties(runner-subprocess PROPERTIES  ENVIRONMENT "TEST_CMD=\"/Volumes/MacDisk/c++/PJC/symcalc/cmake-build-debug/symcalc\"" _BACKTRACE_TRIPLES "/Volumes/MacDisk/c++/PJC/symcalc/CMakeLists.txt;90;add_test;/Volumes/MacDisk/c++/PJC/symcalc/CMakeLists.txt;0;")
