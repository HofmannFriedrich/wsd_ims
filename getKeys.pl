#!/usr/bin/perl
#SBATCH -p 16_cores
#SBATCH --job-name=1Hx1C_getKeys
#SBATCH --mem-per-cpu=1900m
#SBATCH --cpus-per-task=1
#SBATCH --output=log/launch_%j.out
#SBATCH --error=log/launch_%j.err

use strict;
use warnings;
use File::Basename;

my $dir = $ARGV[0];
my $irteerafitx = $ARGV[1];
my $auxfile = $ARGV[1].".data";

#my $fitx = $ARGV[0];

opendir(DH, $dir);
my @files = readdir(DH);
closedir(DH);

open(my $fh, '>', $auxfile ) or die "Ezin izan da fitxategia sortu $!";


foreach my $file (@files)
{
  
    next if ($file eq "." || $file eq "..");
    my $lexelItem = "";
    my $instanceId="";
    my $senseId="";
    
    $lexelItem = $1 if($file =~ /(.+)\.result/g);

    open(DATA, "$dir/$file") or die "Ezin izan da $file fitxategia ireki $!";

    while(<DATA>){
    	my @eremuak = split(" ",$_);
    	$instanceId=$eremuak[1];
    	$senseId=$eremuak[2];

    	print("$lexelItem $instanceId $senseId\n");
    	print($fh "$lexelItem $instanceId $senseId\n");

    }

    
    
}

system("sort $auxfile > $irteerafitx");
system("rm -f $auxfile");

print("Amaitua\n");

close($fh);

1;
