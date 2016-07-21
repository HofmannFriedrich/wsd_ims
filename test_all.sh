#!/bin/bash
if [ $# -lt 1 ]; then
  echo "$0 testXML"
  exit
fi
#test POS
mkdir -p tests/test_POS
sbatch ./test_POS.bash models/model_POS $1 tests/test_POS > log/test_POS.log 2>&1 &
#test surr
mkdir -p tests/test_surr
sbatch ./test_surr.bash models/model_surr $1 tests/test_surr > log/test_surr.log 2>&1 &
#test coll
mkdir -p tests/test_coll
sbatch ./test_coll.bash models/model_coll $1 tests/test_coll > log/test_coll.log 2>&1 &
#test AWE
mkdir -p tests/test_AWE
sbatch ./test_AWE.bash models/model_AWE $1 tests/test_AWE > log/test_AWE.log 2>&1 &
#test CWE
mkdir -p tests/test_CWE
sbatch ./test_CWE.bash models/model_CWE $1 tests/test_CWE > log/test_CWE.log 2>&1 &
# Test Brown
mkdir -p tests/test_brown
sbatch ./test_brown.bash models/model_brown $1 tests/test_brown > log/test_brown.log 2>&1 &
# Test Clark
mkdir -p tests/test_clark
sbatch ./test_clark.bash models/model_clark $1 tests/test_clark > log/test_clark.log 2>&1 &
# Train w2v
mkdir -p tests/test_w2v
sbatch ./test_w2v.bash models/model_w2v $1 tests/test_w2v > log/test_w2v.log 2>&1 &


#test POS+surr+coll
mkdir -p tests/test_c_POS_surr_coll
sbatch ./test_c_POS+surr+coll.bash models/model_c_POS_surr_coll $1 tests/test_c_POS_surr_coll > log/test_POS_surr_coll.log 2>&1 &
#test POS+surr+coll+AWE
mkdir -p tests/test_c_POS_surr_coll_AWE
sbatch ./test_c_POS+surr+coll+AWE.bash models/model_c_POS_surr_coll_AWE $1 tests/test_c_POS_surr_coll_AWE > log/test_POS_surr_coll_AWE.log 2>&1  &
#test POS+surr+coll+CWE
mkdir -p tests/test_c_POS_surr_coll_CWE
sbatch ./test_c_POS+surr+coll+CWE.bash models/model_c_POS_surr_coll_CWE $1 tests/test_c_POS_surr_coll_CWE > log/test_POS_surr_coll_CWE.log 2>&1  &
# test POS+surr+coll+brown
mkdir -p tests/test_c_POS_surr_coll_brown
sbatch ./test_POS+surr+coll+brown models/model_c_POS_surr_coll_brown $1 tests/test_c_POS_surr_coll_brown > log/test_POS_surr_coll_brown.log 2>&1 &
# train POS+surr+coll+clark
mkdir -p tests/test_c_POS_surr_coll_clark
sbatch ./test_POS+surr+coll+clark models/model_c_POS_surr_coll_clark $1 tests/test_c_POS_surr_coll_clark > log/test_POS_surr_coll_clark.log 2>&1 &
# train POS+surr+coll+w2v
mkdir -p tests/test_c_POS_surr_coll_w2v
sbatch ./test_POS+surr+coll+w2v models/model_c_POS_surr_coll_w2v $1 tests/test_c_POS_surr_coll_w2v > log/test_POS_surr_coll_w2v.log 2>&1 &

#test POS+surr+coll+AWE+CWE
mkdir -p tests/test_c_POS_surr_coll_AWE_CWE
sbatch ./test_c_POS+surr+coll+AWE+CWE.bash models/model_c_POS_surr_coll_AWE_CWE $1 tests/test_c_POS_surr_coll_AWE_CWE  > log/test_POS_surr_coll_AWE_CWE.log 2>&1 &
# train POS+surr+coll+brown+clark
mkdir -p tests/test_c_POS_surr_coll_brown_clark
sbatch ./test_POS+surr+coll+brown+clark models/model_c_POS_surr_coll_brown_clark $1 tests/test_c_POS_surr_coll_brown_clark > log/test_c_POS_surr_coll_brown_clark.log 2>&1 &
# train POS+surr+coll+brown+w2v
mkdir -p tests/test_c_POS_surr_coll_brown_w2v
sbatch ./test_POS+surr+coll+brown+w2v models/model_c_POS_surr_coll_brown_w2v $1 tests/test_c_POS_surr_coll_brown_w2v > log/test_c_POS_surr_coll_brown_w2v.log 2>&1 &
# train POS+surr+coll+clark+w2v
mkdir -p tests/test_c_POS_surr_coll_clark_w2v
sbatch ./test_POS+surr+coll+clark+w2v models/model_c_POS_surr_coll_clark+w2v $1 tests/test_c_POS_surr_coll_clark_w2v > log/test_c_POS_surr_coll_clark+w2v.log 2>&1 &

