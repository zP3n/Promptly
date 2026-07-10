# Promptly Mod  
## What is This?  
You know those server-side commands that are super long and a pain to type?  
This mod shortens those commands down to just a few characters.  
Currently, it only supports warp commands in Hypixel Skyblock, and the abbreviated commands are fixed,  
but in the future, we plan to increase flexibility by letting users customize them themselves or adding toggle switches to enable or disable them.  

## How to Use
Please use Fabric 1.21.11, 26.1 or 26.2.  
No libraries other than the Fabric API are required.  
For the time being, we will focus on improving functionality and supporting updates.  
Therefore, we have no plans to support Forge or older versions of Fabric for the time being.  

## Futures  
The file .minecraft/config/promptly.json is generated the first time the application is launched.
The structure of the JSON file is as follows:
```
{
   "1st command you enter":"1st command to send to the server",
   "2nd command you enter":"2nd command to send to the server"
   ...
}
```  
  
/hbc or /holdbreakclick is new function.  
Change Attack/Destroy setting (Hold or Toggle) with sound and text message when you send command.
<img width="1920" height="1009" alt="2026-04-27_01 29 05" src="https://github.com/user-attachments/assets/0109e3ca-5a3c-46db-a3ea-8447c82370eb" />  
  
## Announcement
This is my first time creating a mod, and since I’m using AI to develop it, there may be a few bugs.
Please let me know via issues or pull requests.
