# get POS keys
#./scorer2_linux keys/test_POS.key keys/gs.key > results/POS.result
# get surr keys
#./scorer2_linux keys/test_surr.key keys/gs.key > results/surr.result
# get coll keys
#./scorer2_linux keys/test_coll.key keys/gs.key > results/coll.result
# get AWE keys
#./scorer2_linux keys/test_AWE.key keys/gs.key > results/AWE.result
# get CWE keys
#./scorer2_linux keys/test_CWE.key keys/gs.key > results/CWE.result
# get POS + surr + coll
#./scorer2_linux keys/test_c_POS_surr_coll.key keys/gs.key > results/POS_surr_coll.result
# get POS + surr + coll + AWE + CWE
#./scorer2_linux keys/test_c_POS_surr_coll_AWE_CWE.key keys/gs.key > results/POS_surr_coll_AWE_CWE.result
# get POS + surr + coll + AWE
#./scorer2_linux keys/test_c_POS_surr_coll_AWE.key keys/gs.key > results/POS_surr_coll_AWE.result
# get POS + surr + coll + CWE
#./scorer2_linux keys/test_c_POS_surr_coll_CWE.key keys/gs.key > results/POS_surr_coll_CWE.result


# get Brown
./scorer2_linux keys/test_brown.key  keys/gs.key > results/brown.result
# get Clark
./scorer2_linux keys/test_clark.key  keys/gs.key > results/brown.result
# get W2V
./scorer2_linux keys/test_w2v.key keys/gs.key > results/w2v.result
# get POS+surr+coll+brown
./scorer2_linux keys/test_c_POS_surr_coll_brown.key keys/gs.key > results/w2v.result
# get POS+surr+coll+clark
./scorer2_linux keys/test_c_POS_surr_coll_clark.key keys/gs.key > results/POS+surr+coll+clark.result
# get POS+sur+coll+w2v
./scorer2_linux keys/test_c_POS_surr_coll_w2v.key keys/gs.key > results/POS+surr+coll+w2v.result
# get POS+surr+coll+brown+clark+w2v
./scorer2_linux keys/test_c_POS_surr_coll_brown_clark_w2v.key keys/gs.key > results/POS+surr+coll+brown+clark+w2v.result
