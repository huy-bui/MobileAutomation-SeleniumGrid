#! /bin/bash
osascript -e 'tell app "Terminal"
    do script "cd /Users/trangmy/Documents/seleniumgrid && 
    java -jar selenium-server-standalone.jar -role hub"
end tell'