# get POS keys
#./getKeys.pl tests/test_POS keys/test_POS.key &
# get surr keys
#./getKeys.pl tests/test_surr keys/test_surr.key &
# get coll keys
#./getKeys.pl tests/test_coll keys/test_coll.key &
# get AWE keys
#./getKeys.pl tests/test_AWE keys/test_AWE.key &
# get CWE keys
#./getKeys.pl tests/test_CWE keys/test_CWE.key &
# get POS + surr + coll
#./getKeys.pl tests/test_c_POS_surr_coll keys/test_c_POS_surr_coll.key &
# get POS + surr + coll + AWE + CWE
#./getKeys.pl tests/test_c_POS_surr_coll_AWE_CWE keys/test_c_POS_surr_coll_AWE_CWE.key &
# get POS + surr + coll + AWE
#./getKeys.pl tests/test_c_POS_surr_coll_AWE keys/test_c_POS_surr_coll_AWE.key &
# get POS + surr + coll + CWE
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
