#!/usr/bin/env bash
dir="$(dirname "$(realpath "$0")")"
openssl aes-256-cbc -K $encrypted_ffde4cf4eb9b_key -iv $encrypted_ffde4cf4eb9b_iv -in "$dir/deployKey.enc" -out "$dir/deployKey" -d
eval "$(ssh-agent -s)"
ssh-add "$dir/deployKey"

git remote set-url origin git@github.com:FIUS/JVK-2019.git
git pull

mv target/site/apidocs/ ../

git checkout gh-pages
cp -r ../apidocs/* .
git commit -a -m "Update apidoc"
git push
