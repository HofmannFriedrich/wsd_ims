#!/bin/bash
#SBATCH -p 16_cores
#SBATCH --job-name=1Hx1C_train_surr.bash
#SBATCH --mem-per-cpu=1900m
#SBATCH --cpus-per-task=1
#SBATCH --output=log/launch_%j.out
#SBATCH --error=log/launch_%j.err

if [ $# -lt 3 ]; then
  echo $0 train.xml train.key savedir s2 c2
  exit
fi
s2=0
if [ $# -gt 3 ]; then
  s2=$4
fi
c2=0
if [ $# -gt 4 ]; then
  c2=$5
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
export LANG=en_US
srun java -Xmx1900m -cp $CP sg.edu.nus.comp.nlp.ims.implement.CTrainModel -prop $libdir/prop.xml -ptm $libdir/tag.bin.gz -tagdict $libdir/tagdict.txt -ssm $libdir/EnglishSD.bin.gz $1 $2 $3 -f sg.edu.nus.comp.nlp.ims.feature.CPOSFeatureExtractor -s2 $s2 -c2 $c2 #-type directory


# CP=lib/liblinear-1.33-with-deps.jar:lib/jwnl.jar:lib/commons-logging.jar:lib/jdom.jar:lib/trove.jar:lib/maxent-2.4.0.jar:lib/opennlp-tools-1.3.0.jar:ims.jar
# export LANG=en_US
# java -mx1900m -cp lib/liblinear-1.33-with-deps.jar:lib/jwnl.jar:lib/commons-logging.jar:lib/jdom.jar:lib/trove.jar:lib/maxent-2.4.0.jar:lib/opennlp-tools-1.3.0.jar:ims.jar sg.edu.nus.comp.nlp.ims.implement.CTrainModel -prop lib/prop.xml -ptm lib/tag.bin.gz -tagdict lib/tagdict.txt -ssm lib/EnglishSD.bin.gz examples/bank.n.train.xml examples/bank.n.train.key examples -f sg.edu.nus.comp.nlp.ims.feature.CCWEFeatureExtractor # -s2 $s2 -c2 $c2 #-type directory
