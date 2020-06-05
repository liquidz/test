#!/bin/bash

echo 'plain text1'
echo '::error file=README.md,line=1,col=3::line and column'
echo '::error file=README.md,line=1::line only'
echo 'plain text2'
echo '::error file=README.md::file name only'
echo 'plain text3'

exit 1
