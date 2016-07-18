#!/bin/bash
#SBATCH -p 16_cores
#SBATCH --job-name=1Hx2C_test_POS+surr+coll+AWE.bash
#SBATCH --mem-per-cpu=10900m
#SBATCH --cpus-per-task=2
#SBATCH --output=log/launch_%j.out
#SBATCH --error=log/launch_%j.err

if [ $# -lt 3 ]; then
  echo "$0 modeldir testfile savedir index.sense(option)"
  exit
fi
if (set -u; : $WSDHOME) 2> /dev/null
then
  bdir=$WSDHOME
else
  bdir=$(cd -P -- "$(dirname -- "$0")" && pwd -P)
fi

bdir="/home_cluster/apuerto001/ims"

libdir=$bdir/lib
CP=$bdir:$libdir/liblinear-1.33-with-deps.jar:$libdir/jwnl.jar:$libdir/commons-logging.jar:$libdir/jdom.jar:$libdir/trove.jar:$libdir/maxent-2.4.0.jar:$libdir/opennlp-tools-1.3.0.jar:$bdir/ims.jar
modeldir=$1
testfile=$2
savedir=$3
export LANG=en_US
if [ $# -ge 4 ]; then
srun java -mx30900m -cp $CP sg.edu.nus.comp.nlp.ims.implement.CTester -ptm $libdir/tag.bin.gz -tagdict $libdir/tagdict.txt -ssm $libdir/EnglishSD.bin.gz -prop $libdir/prop.xml -r sg.edu.nus.comp.nlp.ims.io.CResultWriter $testfile $modeldir $modeldir $savedir -is $4 -f sg.edu.nus.comp.nlp.ims.feature.CFeatureExtractorCombination_POS_surr_coll_AWE
else
srun java -mx30900m -cp $CP sg.edu.nus.comp.nlp.ims.implement.CTester -ptm $libdir/tag.bin.gz -tagdict $libdir/tagdict.txt -ssm $libdir/EnglishSD.bin.gz -prop $libdir/prop.xml -r sg.edu.nus.comp.nlp.ims.io.CResultWriter $testfile $modeldir $modeldir $savedir -f sg.edu.nus.comp.nlp.ims.feature.CFeatureExtractorCombination_POS_surr_coll_AWE #-type directory
fi
