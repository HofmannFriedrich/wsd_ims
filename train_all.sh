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
<<<<<<< HEAD
#./train_c_POS_surr_coll.bash $1 $2 models/model_POS_surr_coll > models/model_POS_surr_coll.log 2>&1 &
#train POS+surr+coll+AWE+CWE
#mkdir -p models/model_c_POS_surr_coll_AWE_CWE
#./train_POS+surr+coll+AWE+CWE $1 $2 models/model_c_POS_surr_coll_AWE_CWE > models/model_POS_surr_coll_AWE_CWE.log 2>&1 &
#train POS+surr+coll+AWE
#mkdir -p models/model_c_POS_surr_coll_AWE
#./train_POS+surr+coll+AWE $1 $2 models/model_c_POS_surr_coll_AWE > models/model_POS_surr_coll_AWE.log 2>&1 &
#train POS+surr+coll+CWE
#mkdir -p models/model_c_POS_surr_coll_CWE
#./train_POS+surr+coll+CWE $1 $2 models/model_c_POS_surr_coll_CWE > models/model_POS_surr_coll_CWE.log 2>&1 &
#train Brown
#mkdir -p models/model_brown
#./train_brown.bash $1 $2 models/model_brown > models/model_brown.log 2>&1 &
#train Clark
#mkdir -p models/model_clark
#./train_clark.bash $1 $2 models/model_clark > models/model_clark.log 2>&1 &
#train w2v
#mkdir -p models/model_w2v
#./train_w2v.bash $1 $2 models/model_w2v > models/model_w2v.log 2>&1 &
#train POS+surr+coll+brown
#mkdir -p models/model_c_POS_surr_coll_brown
#./train_POS+surr+coll+brown $1 $2 models/model_c_POS_surr_coll_brown > models/model_POS_surr_coll_brown.log 2>&1 &
#train POS+surr+coll+clark
#mkdir -p models/model_c_POS_surr_coll_clark
#./train_POS+surr+coll+clark $1 $2 models/model_c_POS_surr_coll_clark > models/model_POS_surr_coll_clark.log 2>&1 &
#train POS+surr+coll+w2v
#mkdir -p models/model_c_POS_surr_coll_w2v
#./train_POS+surr+coll+w2v $1 $2 models/model_c_POS_surr_coll_w2v > models/model_POS_surr_coll_w2v.log 2>&1 &
#train POS+surr+coll+brown+clark+w2v
mkdir -p models/model_c_POS_surr_coll_brown_clark_w2v
./train_POS+surr+coll+brown+clark+w2v $1 $2 models/model_c_POS_surr_coll_brown_clark_w2v > models/model_POS_surr_coll_brown_w2v.log 2>&1 &
=======
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
>>>>>>> 80500ca88c41ba18eb7b75f0b57df1a966b4a808
