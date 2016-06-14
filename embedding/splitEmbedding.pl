#!/usr/bin/perl
use strict;
use warnings;
use File::Basename;

my $vocabFile = $ARGV[0];
my $vectorFile = $ARGV[1];

my $embPunc = "emb.punc";
my $embDigi = "emb.dig";
my $embA = "emb.A";
my $embB = "emb.B";
my $embC = "emb.C";
my $embD = "emb.D";
my $embE = "emb.E";
my $embF = "emb.F";
my $embG = "emb.G";
my $embH = "emb.H";
my $embI = "emb.I";
my $embJ = "emb.J";
my $embK = "emb.K";
my $embL = "emb.L";
my $embM = "emb.M";
my $embN = "emb.N";
my $embO = "emb.O";
my $embP = "emb.P";
my $embQ = "emb.Q";
my $embR = "emb.R";
my $embS = "emb.S";
my $embT = "emb.T";
my $embU = "emb.U";
my $embV = "emb.V";
my $embW = "emb.W";
my $embX = "emb.X";
my $embY = "emb.Y";
my $embZ = "emb.Z";

my $vocPunc = "voc.punc";
my $vocDigi = "voc.dig";
my $vocA = "voc.A";
my $vocB = "voc.B";
my $vocC = "voc.C";
my $vocD = "voc.D";
my $vocE = "voc.E";
my $vocF = "voc.F";
my $vocG = "voc.G";
my $vocH = "voc.H";
my $vocI = "voc.I";
my $vocJ = "voc.J";
my $vocK = "voc.K";
my $vocL = "voc.L";
my $vocM = "voc.M";
my $vocN = "voc.N";
my $vocO = "voc.O";
my $vocP = "voc.P";
my $vocQ = "voc.Q";
my $vocR = "voc.R";
my $vocS = "voc.S";
my $vocT = "voc.T";
my $vocU = "voc.U";
my $vocV = "voc.V";
my $vocW = "voc.W";
my $vocX = "voc.X";
my $vocY = "voc.Y";
my $vocZ = "voc.Z";

open(my $fhPunc, '>', $embPunc ) or die "$embPunc could not be created $!";
open(my $fhDigi, '>', $embDigi ) or die "$embDigi could not be created $!";
open(my $fhA, '>', $embA ) or die "$embA could not be created $!";
open(my $fhB, '>', $embB ) or die "$embB could not be created $!";
open(my $fhC, '>', $embC ) or die "$embC could not be created $!";
open(my $fhD, '>', $embD ) or die "$embD could not be created $!";
open(my $fhE, '>', $embE ) or die "$embE could not be created $!";
open(my $fhF, '>', $embF ) or die "$embF could not be created $!";
open(my $fhG, '>', $embG ) or die "$embG could not be created $!";
open(my $fhH, '>', $embH ) or die "$embH could not be created $!";
open(my $fhI, '>', $embI ) or die "$embI could not be created $!";
open(my $fhJ, '>', $embJ ) or die "$embJ could not be created $!";
open(my $fhK, '>', $embK ) or die "$embK could not be created $!";
open(my $fhL, '>', $embL ) or die "$embL could not be created $!";
open(my $fhM, '>', $embM ) or die "$embM could not be created $!";
open(my $fhN, '>', $embN ) or die "$embN could not be created $!";
open(my $fhO, '>', $embO ) or die "$embO could not be created $!";
open(my $fhP, '>', $embP ) or die "$embP could not be created $!";
open(my $fhQ, '>', $embQ ) or die "$embQ could not be created $!";
open(my $fhR, '>', $embR ) or die "$embR could not be created $!";
open(my $fhS, '>', $embS ) or die "$embS could not be created $!";
open(my $fhT, '>', $embT ) or die "$embT could not be created $!";
open(my $fhU, '>', $embU ) or die "$embU could not be created $!";
open(my $fhV, '>', $embV ) or die "$embV could not be created $!";
open(my $fhW, '>', $embW ) or die "$embW could not be created $!";
open(my $fhX, '>', $embX ) or die "$embX could not be created $!";
open(my $fhY, '>', $embY ) or die "$embY could not be created $!";
open(my $fhZ, '>', $embZ ) or die "$embZ could not be created $!";

open(my $fhvPunc, '>', $vocPunc ) or die "$vocPunc could not be created $!";
open(my $fhvDigi, '>', $vocDigi ) or die "$vocDigi could not be created $!";
open(my $fhvA, '>', $vocA ) or die "$vocA could not be created $!";
open(my $fhvB, '>', $vocB ) or die "$vocB could not be created $!";
open(my $fhvC, '>', $vocC ) or die "$vocC could not be created $!";
open(my $fhvD, '>', $vocD ) or die "$vocD could not be created $!";
open(my $fhvE, '>', $vocE ) or die "$vocE could not be created $!";
open(my $fhvF, '>', $vocF ) or die "$vocF could not be created $!";
open(my $fhvG, '>', $vocG ) or die "$vocG could not be created $!";
open(my $fhvH, '>', $vocH ) or die "$vocH could not be created $!";
open(my $fhvI, '>', $vocI ) or die "$vocI could not be created $!";
open(my $fhvJ, '>', $vocJ ) or die "$vocJ could not be created $!";
open(my $fhvK, '>', $vocK ) or die "$vocK could not be created $!";
open(my $fhvL, '>', $vocL ) or die "$vocL could not be created $!";
open(my $fhvM, '>', $vocM ) or die "$vocM could not be created $!";
open(my $fhvN, '>', $vocN ) or die "$vocN could not be created $!";
open(my $fhvO, '>', $vocO ) or die "$vocO could not be created $!";
open(my $fhvP, '>', $vocP ) or die "$vocP could not be created $!";
open(my $fhvQ, '>', $vocQ ) or die "$vocQ could not be created $!";
open(my $fhvR, '>', $vocR ) or die "$vocR could not be created $!";
open(my $fhvS, '>', $vocS ) or die "$vocS could not be created $!";
open(my $fhvT, '>', $vocT ) or die "$vocT could not be created $!";
open(my $fhvU, '>', $vocU ) or die "$vocU could not be created $!";
open(my $fhvV, '>', $vocV ) or die "$vocV could not be created $!";
open(my $fhvW, '>', $vocW ) or die "$vocW could not be created $!";
open(my $fhvX, '>', $vocX ) or die "$vocX could not be created $!";
open(my $fhvY, '>', $vocY ) or die "$vocY could not be created $!";
open(my $fhvZ, '>', $vocZ ) or die "$vocZ could not be created $!";

open(my $fhVocab, '<', $vocabFile) or die "$vocabFile could not be created $!";
open(my $fhVector, '<', $vectorFile) or die "$vocabFile could not be created $!";

my $line_number=1;
my $lineVocab;
my $lineVector;
my $firstChar;
    
while(!eof($fhVocab) && !eof($fhVector)){
    
    $lineVocab = <$fhVocab>;
    $lineVector = <$fhVector>;
    $firstChar = substr(lc($lineVocab),0,1);
    
    if($firstChar =~ /[0-9]/){print($fhDigi $lineVector);print($fhvDigi $lineVocab);}
    elsif($firstChar eq "a"){print($fhA $lineVector);print($fhvA $lineVocab);}
    elsif($firstChar eq "b"){print($fhB $lineVector);print($fhvB $lineVocab);}
    elsif($firstChar eq "c"){print($fhC $lineVector);print($fhvC $lineVocab);}
    elsif($firstChar eq "d"){print($fhD $lineVector);print($fhvD $lineVocab);}
    elsif($firstChar eq "e"){print($fhE $lineVector);print($fhvE $lineVocab);}
    elsif($firstChar eq "f"){print($fhF $lineVector);print($fhvF $lineVocab);}
    elsif($firstChar eq "g"){print($fhG $lineVector);print($fhvG $lineVocab);}
    elsif($firstChar eq "h"){print($fhH $lineVector);print($fhvH $lineVocab);}
    elsif($firstChar eq "i"){print($fhI $lineVector);print($fhvI $lineVocab);}
    elsif($firstChar eq "j"){print($fhJ $lineVector);print($fhvJ $lineVocab);}
    elsif($firstChar eq "k"){print($fhK $lineVector);print($fhvK $lineVocab);}
    elsif($firstChar eq "l"){print($fhL $lineVector);print($fhvL $lineVocab);}
    elsif($firstChar eq "m"){print($fhM $lineVector);print($fhvM $lineVocab);}
    elsif($firstChar eq "n"){print($fhN $lineVector);print($fhvN $lineVocab);}
    elsif($firstChar eq "o"){print($fhO $lineVector);print($fhvO $lineVocab);}
    elsif($firstChar eq "p"){print($fhP $lineVector);print($fhvP $lineVocab);}
    elsif($firstChar eq "q"){print($fhQ $lineVector);print($fhvQ $lineVocab);}
    elsif($firstChar eq "r"){print($fhR $lineVector);print($fhvR $lineVocab);}
    elsif($firstChar eq "s"){print($fhS $lineVector);print($fhvS $lineVocab);}
    elsif($firstChar eq "t"){print($fhT $lineVector);print($fhvT $lineVocab);}
    elsif($firstChar eq "u"){print($fhU $lineVector);print($fhvU $lineVocab);}
    elsif($firstChar eq "v"){print($fhV $lineVector);print($fhvV $lineVocab);}
    elsif($firstChar eq "w"){print($fhW $lineVector);print($fhvW $lineVocab);}
    elsif($firstChar eq "x"){print($fhX $lineVector);print($fhvX $lineVocab);}
    elsif($firstChar eq "y"){print($fhY $lineVector);print($fhvY $lineVocab);}
    elsif($firstChar eq "z"){print($fhZ $lineVector);print($fhvZ $lineVocab);}
    else{print($fhPunc $lineVector);print($fhvPunc $lineVocab);}

}


close($fhVocab);
close($fhVector);

close($fhvPunc);
close($fhvDigi);
close($fhvA);
close($fhvB);
close($fhvC);
close($fhvD);
close($fhvE);
close($fhvF);
close($fhvG);
close($fhvH);
close($fhvI);
close($fhvJ);
close($fhvK);
close($fhvL);
close($fhvM);
close($fhvN);
close($fhvO);
close($fhvP);
close($fhvQ);
close($fhvR);
close($fhvS);
close($fhvT);
close($fhvU);
close($fhvV);
close($fhvW);
close($fhvX);
close($fhvY);
close($fhvZ);

close($fhPunc);
close($fhDigi);
close($fhA);
close($fhB);
close($fhC);
close($fhD);
close($fhE);
close($fhF);
close($fhG);
close($fhH);
close($fhI);
close($fhJ);
close($fhK);
close($fhL);
close($fhM);
close($fhN);
close($fhO);
close($fhP);
close($fhQ);
close($fhR);
close($fhS);
close($fhT);
close($fhU);
close($fhV);
close($fhW);
close($fhX);
close($fhY);
close($fhZ);
1;
