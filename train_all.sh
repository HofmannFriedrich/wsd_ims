#!/bin/bash
if [ $# -lt 2 ]; then
  echo "$0 trainXML trainKEY"
  exit
fi
# # Train POS
#mkdir -p models/model_POS
#sbatch ./train_POS.bash $1 $2 models/model_POS > models/model_POS.log 2>&1 &
# Train surr
#mkdir -p models/model_surr
#sbatch ./train_surr.bash $1 $2 models/model_surr > models/model_surr.log 2>&1 &
#train coll
#mkdir -p models/model_coll
#sbatch ./train_coll.bash $1 $2 models/model_coll > models/model_coll.log 2>&1 &
# #train AWE
#mkdir -p models/model_AWE
#sbatch ./train_AWE.bash $1 $2 models/model_AWE > models/model_AWE.log 2>&1 &
#train CWE
#mkdir -p models/model_CWE
#sbatch ./train_CWE.bash $1 $2 models/model_CWE > models/model_CWE.log 2>&1 &
# #train POS+surr+coll
#mkdir -p models/model_POS_surr_coll
#sbatch ./train_c_POS_surr_coll.bash $1 $2 models/model_POS_surr_coll > models/model_POS_surr_coll.log 2>&1 &
# #train POS+surr+coll+AWE+CWE
#mkdir -p models/model_c_POS_surr_coll_AWE_CWE
#sbatch ./train_POS+surr+coll+AWE+CWE $1 $2 models/model_c_POS_surr_coll_AWE_CWE > models/model_POS_surr_coll_AWE_CWE.log 2>&1 &
# #train POS+surr+coll+AWE
#mkdir -p models/model_c_POS_surr_coll_AWE
#sbatch ./train_POS+surr+coll+AWE $1 $2 models/model_c_POS_surr_coll_AWE > models/model_POS_surr_coll_AWE.log 2>&1 &
# #train POS+surr+coll+CWE
mkdir -p models/model_c_POS_surr_coll_CWE
sbatch ./train_POS+surr+coll+CWE $1 $2 models/model_c_POS_surr_coll_CWE > models/model_POS_surr_coll_CWE.log 2>&1 &
