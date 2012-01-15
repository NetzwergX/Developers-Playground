#!/bin/bash

# GIT Repository update script by Sebastian Teumert
# !Important! 
# Pulls & pushes only the 'master' branch
# Remotes have to be named 'origin' for pulling and 'upstream' for pushing
# If no remote named 'upstream' exists, the repo will not be pushed


FILES="$PWD/*/"
for f in $FILES
do
  echo "########################################"
  echo "Processing $f ..." 
  cd $f    
  test -d "$f.git/" && echo "GIT repository exists" || echo "GIT repository does not exists"
  if [ -d "$f.git/" ];
  then
   git remote show | grep origin > /dev/null && git pull origin master 
   git remote show | grep upstream > /dev/null && git push upstream master
  fi
  cd ..
done

