# get POS keys
./scorer2_linux keys/test_POS.key keys/english-lexical-sample.test.key > results/POS.result
# get surr keys
./scorer2_linux keys/test_surr.key keys/english-lexical-sample.test.key > results/surr.result
# get coll keys
./scorer2_linux keys/test_coll.key keys/english-lexical-sample.test.key > results/coll.result
# get AWE keys
./scorer2_linux keys/test_AWE.key keys/english-lexical-sample.test.key > results/AWE.result
# get CWE keys
./scorer2_linux keys/test_CWE.key keys/english-lexical-sample.test.key > results/CWE.result
# get POS + surr + coll
./scorer2_linux keys/test_c_POS_surr_coll.key keys/english-lexical-sample.test.key > results/POS_surr_coll.result
# get POS + surr + coll + AWE + CWE
./scorer2_linux keys/test_c_POS_surr_coll_AWE_CWE.key keys/english-lexical-sample.test.key > results/POS_surr_coll_AWE_CWE.result
# get POS + surr + coll + AWE
./scorer2_linux keys/test_c_POS_surr_coll_AWE.key keys/english-lexical-sample.test.key > results/POS_surr_coll_AWE.result
# get POS + surr + coll + CWE
./scorer2_linux keys/test_c_POS_surr_coll_CWE.key keys/english-lexical-sample.test.key > results/POS_surr_coll_CWE.result
