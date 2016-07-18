#!/bin/bash


bdir="/home_cluster/apuerto001/ims"

# get POS keys
srun ./getKeys.pl $bdir/tests/test_POS $bdir/keys/test_POS.key > $bdir/keys/key_POS.log 2>&1 &
# get surr keys
srun ./getKeys.pl $bdir/tests/test_surr $bdir/keys/test_surr.key > $bdir/keys/key_surr.log 2>&1 &
# get coll keys
srun ./getKeys.pl $bdir/tests/test_coll $bdir/keys/test_coll.key > $bdir/keys/key_coll.log 2>&1 &
# get AWE keys
srun ./getKeys.pl $bdir/tests/test_AWE $bdir/keys/test_AWE.key > $bdir/keys/key_AWE.log 2>&1 &
# get CWE keys
srun ./getKeys.pl $bdir/tests/test_CWE $bdir/keys/test_CWE.key > $bdir/keys/key_CWE.log 2>&1 &
# get POS + surr + coll
srun ./getKeys.pl $bdir/tests/test_c_POS_surr_coll $bdir/keys/test_c_POS_surr_coll.key > $bdir/keys/key_POS_surr_coll.log 2>&1 &
# get POS + surr + coll + AWE + CWE
<<<<<<< HEAD
#./getKeys.pl tests/test_c_POS_surr_coll_AWE_CWE keys/test_c_POS_surr_coll_AWE_CWE.key &
=======
srun ./getKeys.pl $bdir/tests/test_c_POS_surr_coll_AWE_CWE $bdir/keys/test_c_POS_surr_coll_AWE_CWE.key > $bdir/keys/key_POS_surr_coll_AWE_CWE.log  2>&1 &
>>>>>>> 80500ca88c41ba18eb7b75f0b57df1a966b4a808
# get POS + surr + coll + AWE
srun ./getKeys.pl $bdir/tests/test_c_POS_surr_coll_AWE $bdir/keys/test_c_POS_surr_coll_AWE.key > $bdir/keys/key_POS_surr_coll_AWE.log 2>&1 &
# get POS + surr + coll + CWE
<<<<<<< HEAD
#./getKeys.pl tests/test_c_POS_surr_coll_CWE keys/test_c_POS_surr_coll_CWE.key &
# get Brown
./getKeys.pl tests/test_brown keys/test_brown.key  &
# get Clark
./getKeys.pl tests/test_clark keys/test_clark.key  &
# get W2V
./getKeys.pl tests/test_w2v keys/test_w2v.key &
# get POS+surr+coll+brown
./getKeys.pl tests/test_c_POS_surr_coll_brown keys/test_c_POS_surr_coll_brown.key &
# get POS+surr+coll+clark
./getKeys.pl tests/test_c_POS_surr_coll_clark keys/test_c_POS_surr_coll_clark.key &
# get POS+sur+coll+w2v
./getKeys.pl tests/test_c_POS_surr_coll_w2v keys/test_c_POS_surr_coll_w2v.key &
# get POS+surr+coll+brown+clark+w2v
./getKeys.pl tests/test_c_POS_surr_coll_brown_clark_w2v keys/test_c_POS_surr_coll_brown_clark_w2v.key
=======
srun ./getKeys.pl $bdir/tests/test_c_POS_surr_coll_CWE $bdir/keys/test_c_POS_surr_coll_CWE.key > $bdir/keys/key_POS_surr_coll_CWE.log 2>&1 &
>>>>>>> 80500ca88c41ba18eb7b75f0b57df1a966b4a808
