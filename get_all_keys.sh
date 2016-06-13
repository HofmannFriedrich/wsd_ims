# get POS keys
./getKeys.pl tests/test_POS keys/test_POS.key &
# get surr keys
./getKeys.pl tests/test_surr keys/test_surr.key &
# get coll keys
./getKeys.pl tests/test_coll keys/test_coll.key &
# get AWE keys
./getKeys.pl tests/test_AWE keys/test_AWE.key &
# get CWE keys
./getKeys.pl tests/test_CWE keys/test_CWE.key &
# get POS + surr + coll
./getKeys.pl tests/test_c_POS_surr_coll keys/test_c_POS_surr_coll.key &
# get POS + surr + coll + AWE + CWE
./getKeys.pl tests/test_c_POS_surr_coll_AWE_CWE keys/test_c_POS_surr_coll_AWE_CWE.key &
# get POS + surr + coll + AWE
./getKeys.pl tests/test_c_POS_surr_coll_AWE keys/test_c_POS_surr_coll_AWE.key &
# get POS + surr + coll + CWE
./getKeys.pl tests/test_c_POS_surr_coll_CWE keys/test_c_POS_surr_coll_CWE.key &
