#!/usr/bin/perl
use strict;
use warnings;
use File::Basename;

my $file = $ARGV[0];
my $irteerafitx = $ARGV[1];
my $auxfile = $ARGV[1].".data";

open(my $fh, '>', $auxfile ) or die "Ezin izan da fitxategia sortu $!";

my $lexelItem = "";
my $instanceId="";
my $senseId="";
    
open(DATA, $file) or die "Ezin izan da $file fitxategia ireki $!";

while(<DATA>){
    	
	$lexelItem = "" if($_ =~ /<\/lexelt>/g);
	$instanceId = "" if($_ =~ /\<\/instance\>/g);
	$senseId = "" if($_ =~ /\<\/instance\>/g);
	
	$lexelItem = $1 if($_ =~ /item=\"([^\"]+)\"/g);
#	$instanceId= $1 if($_ =~ /instance=\"([^\"]+)\"/g);
#	$senseId = $1 if($_ =~ /senseid=\"([^\"]+)\"/g);

	($instanceId, $senseId) = ($1, $2) if($_ =~ /<answer instance=\"([^\"]+)\" senseid=\"([^\"]+)\"/g);

	# <answer instance="11:0@14@wsj/10/wsj_1065@wsj@en@on" senseid="1" wn="1,2,3" wn-version="2.1"/>
	
	if($lexelItem ne "" && $instanceId ne "" && $senseId ne ""){
	    print("$lexelItem $instanceId $senseId\n");
	    print($fh "$lexelItem $instanceId $senseId\n");
	    $instanceId = "";
	    $senseId = "";
	}
	
}

system("sort $auxfile > $irteerafitx");
system("rm -f $auxfile");


close($fh);

1;
