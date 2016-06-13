t f#!/usr/bin/perl
use strict;
use warnings;
use File::Basename;

my $dir = $ARGV[0];
my $irteerafitx = $ARGV[1];

#my $fitx = $ARGV[0];

opendir(DH, $dir);
my @files = readdir(DH);
closedir(DH);

open(my $fh, '>', $irteerafitx ) or die "Ezin izan da $irteerafitx fitxategia sortu $!";


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
	
#	print("$lexelItem\t$instanceId\t$senseId\n");
	print($fh "$lexelItem $instanceId $senseId\n");
    }
    
    
}

print("WARNING: $irteerafitx is not sorted, run sort $irteerafitx to sort it\n");

close($fh);

1;
