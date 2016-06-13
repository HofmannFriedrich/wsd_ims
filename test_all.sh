#!/bin/bash
if [ $# -lt 1 ]; then
  echo "$0 testXML"
  exit
fi
#test POS
mkdir -p tests/test_POS
./test_POS.bash models/model_POS $1 tests/test_POS > tests/test_POS.log 2>&1 &
#test surr
mkdir -p tests/test_surr
./test_surr.bash models/model_surr $1 tests/test_surr > tests/test_surr.log 2>&1 &
#test coll
mkdir -p tests/test_coll
./test_coll.bash models/model_coll $1 tests/test_coll > tests/test_coll.log 2>&1 &
#test AWE
mkdir -p tests/test_AWE
./test_AWE.bash models/model_AWE $1 tests/test_AWE > tests/test_AWE.log 2>&1 &
#test CWE
mkdir -p tests/test_CWE
./test_CWE.bash models/model_CWE $1 tests/test_CWE > tests/test_CWE.log 2>&1 &
#test POS+surr+coll
mkdir -p tests/test_c_POS_surr_coll
./test_c_POS+surr+coll.bash models/model_c_POS_surr_coll $1 tests/test_c_POS_surr_coll > tests/test_POS_surr_coll.log 2>&1 &
#test POS+surr+coll+AWE+CWE
mkdir -p tests/test_c_POS_surr_coll_AWE_CWE
./test_c_POS+surr+coll+AWE+CWE.bash models/model_c_POS_surr_coll_AWE_CWE $1 tests/test_c_POS_surr_coll_AWE_CWE  > tests/test_POS_surr_coll_AWE_CWE.log 2>&1 &
#test POS+surr+coll+AWE
mkdir -p tests/test_c_POS_surr_coll_AWE
./test_c_POS+surr+coll+AWE.bash models/model_model_c_POS_surr_coll_AWE $1 tests/test_c_POS_surr_coll_AWE > tests/test_POS_surr_coll_AWE.log 2>&1  &
#test POS+surr+coll+CWE
mkdir -p tests/test_c_POS_surr_coll_CWE
./test_c_POS+surr+coll+CWE.bash models/model_model_c_POS_surr_coll_CWE $1 tests/test_c_POS_surr_coll_CWE > tests/test_POS_surr_coll_CWE.log 2>&1  &
