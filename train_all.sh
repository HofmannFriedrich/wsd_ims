#!/bin/bash
if [ $# -lt 2 ]; then
  echo "$0 trainXML trainKEY"
  exit
fi
# Train POS
mkdir -p models/model_POS
sbatch ./train_POS.bash $1 $2 models/model_POS > log/model_POS.log 2>&1 &
# Train surr
mkdir -p models/model_surr
sbatch ./train_surr.bash $1 $2 models/model_surr > log/model_surr.log 2>&1 &
# Train coll
mkdir -p models/model_coll
sbatch ./train_coll.bash $1 $2 models/model_coll > log/model_coll.log 2>&1 &
# train AWE
mkdir -p models/model_AWE
sbatch ./train_AWE.bash $1 $2 models/model_AWE > log/model_AWE.log 2>&1 &
# Train CWE
mkdir -p models/model_CWE
sbatch ./train_CWE.bash $1 $2 models/model_CWE > log/model_CWE.log 2>&1 &
# Train Brown
mkdir -p models/model_brown
sbatch ./train_brown.bash $1 $2 models/model_brown > log/model_brown.log 2>&1 &
# Train Clark
mkdir -p models/model_clark
sbatch ./train_clark.bash $1 $2 models/model_clark > log/model_clark.log 2>&1 &
# Train w2v
mkdir -p models/model_w2v
sbatch ./train_w2v.bash $1 $2 models/model_w2v > log/model_w2v.log 2>&1 &

# train POS+surr+coll
mkdir -p models/model_POS_surr_coll
sbatch ./train_c_POS_surr_coll.bash $1 $2 models/model_POS_surr_coll > log/model_POS_surr_coll.log 2>&1 &
# train POS+surr+coll+AWE
mkdir -p models/model_c_POS_surr_coll_AWE
sbatch ./train_POS+surr+coll+AWE $1 $2 models/model_c_POS_surr_coll_AWE > log/model_POS_surr_coll_AWE.log 2>&1 &
# train POS+surr+coll+CWE
mkdir -p models/model_c_POS_surr_coll_CWE
sbatch ./train_POS+surr+coll+CWE $1 $2 models/model_c_POS_surr_coll_CWE > log/model_POS_surr_coll_CWE.log 2>&1 &
# train POS+surr+coll+brown
mkdir -p models/model_c_POS_surr_coll_brown
sbatch ./train_POS+surr+coll+brown $1 $2 models/model_c_POS_surr_coll_brown > log/model_POS_surr_coll_brown.log 2>&1 &
# train POS+surr+coll+clark
mkdir -p models/model_c_POS_surr_coll_clark
sbatch ./train_POS+surr+coll+clark $1 $2 models/model_c_POS_surr_coll_clark > log/model_POS_surr_coll_clark.log 2>&1 &
# train POS+surr+coll+w2v
mkdir -p models/model_c_POS_surr_coll_w2v
sbatch ./train_POS+surr+coll+w2v $1 $2 models/model_c_POS_surr_coll_w2v > log/model_POS_surr_coll_w2v.log 2>&1 &
# train POS+surr+coll+brown+clark
mkdir -p models/model_c_POS_surr_coll_brown_clark
sbatch ./train_POS+surr+coll+brown+clark $1 $2 models/model_c_POS_surr_coll_brown_clark > log/model_c_POS_surr_coll_brown_clark.log 2>&1 &
# train POS+surr+coll+brown+w2v
mkdir -p models/model_c_POS_surr_coll_brown_w2v
sbatch ./train_POS+surr+coll+brown+w2v $1 $2 models/model_c_POS_surr_coll_brown_w2v > log/model_c_POS_surr_coll_brown_w2v.log 2>&1 &
# train POS+surr+coll+clark+w2v
mkdir -p models/model_c_POS_surr_coll_clark_w2v
sbatch ./train_POS+surr+coll+clark+w2v $1 $2 models/model_c_POS_surr_coll_clark+w2v > log/model_c_POS_surr_coll_clark+w2v.log 2>&1 &

#train POS+surr+coll+AWE+CWE
mkdir -p models/model_c_POS_surr_coll_AWE_CWE
sbatch ./train_POS+surr+coll+AWE+CWE $1 $2 models/model_c_POS_surr_coll_AWE_CWE > log/model_POS_surr_coll_AWE_CWE.log 2>&1 &
# train POS+surr+coll+brown+clark+w2v
mkdir -p models/model_c_POS_surr_coll_brown_clark_w2v
sbatch ./train_POS+surr+coll+brown+clark+w2v $1 $2 models/model_c_POS_surr_coll_brown_clark_w2v > log/model_POS_surr_coll_brown_w2v.log 2>&1 &

