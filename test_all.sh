#!/bin/bash
if [ $# -lt 1 ]; then
  echo "$0 testXML"
  exit
fi
#test POS
#mkdir -p tests/test_POS
#sbatch ./test_POS.bash models/model_POS $1 tests/test_POS > tests/test_POS.log 2>&1 &
#test surr
#mkdir -p tests/test_surr
#sbatch ./test_surr.bash models/model_surr $1 tests/test_surr > tests/test_surr.log 2>&1 &
#test coll
#mkdir -p tests/test_coll
#sbatch ./test_coll.bash models/model_coll $1 tests/test_coll > tests/test_coll.log 2>&1 &
#test AWE
#mkdir -p tests/test_AWE
#sbatch ./test_AWE.bash models/model_AWE $1 tests/test_AWE > tests/test_AWE.log 2>&1 &
#test CWE
#mkdir -p tests/test_CWE
#sbatch ./test_CWE.bash models/model_CWE $1 tests/test_CWE > tests/test_CWE.log 2>&1 &
#test POS+surr+coll
mkdir -p tests/test_c_POS_surr_coll
sbatch ./test_c_POS+surr+coll.bash models/model_c_POS_surr_coll $1 tests/test_c_POS_surr_coll > tests/test_POS_surr_coll.log 2>&1 &
#test POS+surr+coll+AWE+CWE
#mkdir -p tests/test_c_POS_surr_coll_AWE_CWE
<<<<<<< HEAD
#./test_c_POS+surr+coll+AWE+CWE.bash models/model_c_POS_surr_coll_AWE_CWE $1 tests/test_c_POS_surr_coll_AWE_CWE  > tests/test_POS_surr_coll_AWE_CWE.log 2>&1 &
=======
#sbatch ./test_c_POS+surr+coll+AWE+CWE.bash models/model_c_POS_surr_coll_AWE_CWE $1 tests/test_c_POS_surr_coll_AWE_CWE  > tests/test_POS_surr_coll_AWE_CWE.log 2>&1 &
>>>>>>> 80500ca88c41ba18eb7b75f0b57df1a966b4a808
#test POS+surr+coll+AWE
#mkdir -p tests/test_c_POS_surr_coll_AWE
#sbatch ./test_c_POS+surr+coll+AWE.bash models/model_model_c_POS_surr_coll_AWE $1 tests/test_c_POS_surr_coll_AWE > tests/test_POS_surr_coll_AWE.log 2>&1  &
#test POS+surr+coll+CWE
#mkdir -p tests/test_c_POS_surr_coll_CWE
<<<<<<< HEAD
#./test_c_POS+surr+coll+CWE.bash models/model_model_c_POS_surr_coll_CWE $1 tests/test_c_POS_surr_coll_CWE > tests/test_POS_surr_coll_CWE.log 2>&1  &
#test Brown
#mkdir -p tests/test_brown
#./test_brown.bash models/model_brown $1 tests/test_brown > tests/test_brown.log 2>&1  &
#test Clark
#mkdir -p tests/test_clark
#./test_clark.bash models/model_clark $1 tests/test_clark > tests/test_clark.log 2>&1  &
#test W2V
#mkdir -p tests/test_w2v
#./test_w2v.bash models/model_w2v $1 tests/test_w2v > tests/test_w2v.log 2>&1  &
#test POS+surr+coll+brown
#mkdir -p tests/test_c_POS_surr_coll_brown
#./test_c_POS+surr+coll+brown.bash models/model_c_POS_surr_coll_brown $1 tests/test_c_POS_surr_coll_brown > tests/test_c_POS_surr_coll_brown.log 2>&1 &
#test POS+surr+coll+clark
#mkdir -p tests/test_c_POS_surr_coll_clark
#./test_c_POS+surr+coll+clark.bash models/model_c_POS_surr_coll_clark $1 tests/test_c_POS_surr_coll_clark > tests/test_c_POS_surr_coll_clark.log 2>&1 &
#test POS+sur+coll+w2v
#mkdir -p tests/test_c_POS_surr_coll_w2v
#./test_c_POS+surr+coll+w2v.bash models/model_c_POS_surr_coll_w2v $1 tests/test_c_POS_surr_coll_w2v > tests/test_c_POS_surr_coll_w2v.log
#test POS+surr+coll+brown+clark+w2v
mkdir -p tests/test_c_POS_surr_coll_brown_clark_w2v
./test_c_POS+surr+coll+brown+clark+w2v.bash models/model_c_POS_surr_coll_brown_clark_w2v $1 tests/test_c_POS_surr_coll_brown_clark_w2v > tests/test_c_POS_surr_coll_brown_clark_w2v.log 2>&1 &
=======
#sbatch ./test_c_POS+surr+coll+CWE.bash models/model_model_c_POS_surr_coll_CWE $1 tests/test_c_POS_surr_coll_CWE > tests/test_POS_surr_coll_CWE.log 2>&1  &
>>>>>>> 80500ca88c41ba18eb7b75f0b57df1a966b4a808
