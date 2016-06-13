# Train POS
./test_POS.bash models/model_POS task17/test/lexical-sample/english-lexical-sample.test.xml tests/test_POS &
# Train surr
./test_surr.bash models/model_surr task17/test/lexical-sample/english-lexical-sample.test.xml tests/test_surr &
#train coll
./test_coll.bash models/model_coll task17/test/lexical-sample/english-lexical-sample.test.xml tests/test_coll &
#train AWE
./test_AWE.bash models/model_AWE task17/test/lexical-sample/english-lexical-sample.test.xml tests/test_AWE &
#train CWE
./test_CWE.bash models/model_CWE task17/test/lexical-sample/english-lexical-sample.test.xml tests/test_CWE &
#train POS+surr+coll+AWE+CWE
./test_c_POS+surr+coll+AWE+CWE.bash models/model_c_POS_surr_coll_AWE_CWE task17/test/lexical-sample/english-lexical-sample.test.xml tests/test_c_POS_surr_coll_AWE_CWE &
#train POS+surr+coll+AWE
./test_c_POS+surr+coll+AWE.bash models/model_model_c_POS_surr_coll_AWE task17/test/lexical-sample/english-lexical-sample.test.xml tests/test_c_POS_surr_coll_AWE &
#train POS+surr+coll+CWE
./test_c_POS+surr+coll+CWE.bash models/model_model_c_POS_surr_coll_CWE task17/test/lexical-sample/english-lexical-sample.test.xml tests/test_c_POS_surr_coll_CWE &
