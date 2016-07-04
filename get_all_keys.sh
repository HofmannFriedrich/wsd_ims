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
srun ./getKeys.pl $bdir/tests/test_c_POS_surr_coll_AWE_CWE $bdir/keys/test_c_POS_surr_coll_AWE_CWE.key > $bdir/keys/key_POS_surr_coll_AWE_CWE.log  2>&1 &
# get POS + surr + coll + AWE
srun ./getKeys.pl $bdir/tests/test_c_POS_surr_coll_AWE $bdir/keys/test_c_POS_surr_coll_AWE.key > $bdir/keys/key_POS_surr_coll_AWE.log 2>&1 &
# get POS + surr + coll + CWE
srun ./getKeys.pl $bdir/tests/test_c_POS_surr_coll_CWE $bdir/keys/test_c_POS_surr_coll_CWE.key > $bdir/keys/key_POS_surr_coll_CWE.log 2>&1 &
