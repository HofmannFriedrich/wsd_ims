#test POS
./test_POS.bash models/model_POS task17/test/lexical-sample/english-lexical-sample.test.xml tests/test_POS > tests/test_POS.log 2>&1 &
#test surr
./test_surr.bash models/model_surr task17/test/lexical-sample/english-lexical-sample.test.xml tests/test_surr > tests/test_surr.log 2>&1 &
#test coll
./test_coll.bash models/model_coll task17/test/lexical-sample/english-lexical-sample.test.xml tests/test_coll > tests/test_coll.log 2>&1 &
#test AWE
./test_AWE.bash models/model_AWE task17/test/lexical-sample/english-lexical-sample.test.xml tests/test_AWE > tests/test_AWE.log 2>&1 &
#test CWE
./test_CWE.bash models/model_CWE task17/test/lexical-sample/english-lexical-sample.test.xml tests/test_CWE > tests/test_CWE.log 2>&1 &
#test POS+surr+coll
./test_c_POS+surr+coll.bash models/model_c_POS_surr_coll task17/test/lexical-sample/english-lexical-sample.test.xml tests/test_c_POS_surr_coll > tests/test_POS_surr_coll.log 2>&1 &
#test POS+surr+coll+AWE+CWE
./test_c_POS+surr+coll+AWE+CWE.bash models/model_c_POS_surr_coll_AWE_CWE task17/test/lexical-sample/english-lexical-sample.test.xml tests/test_c_POS_surr_coll_AWE_CWE  > tests/test_POS_surr_coll_AWE_CWE.log 2>&1 &
#test POS+surr+coll+AWE
./test_c_POS+surr+coll+AWE.bash models/model_model_c_POS_surr_coll_AWE task17/test/lexical-sample/english-lexical-sample.test.xml tests/test_c_POS_surr_coll_AWE > tests/test_POS_surr_coll_AWE.log 2>&1  &
#test POS+surr+coll+CWE
./test_c_POS+surr+coll+CWE.bash models/model_model_c_POS_surr_coll_CWE task17/test/lexical-sample/english-lexical-sample.test.xml tests/test_c_POS_surr_coll_CWE > tests/test_POS_surr_coll_CWE.log 2>&1  &
