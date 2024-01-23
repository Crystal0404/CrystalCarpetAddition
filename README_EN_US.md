## Crystal Carpet Additon

    
  [中文](https://github.com/Crystal0404/CrystalCarpetAddition) / *English*

  *I have finally completed it!*

A [Carpet mod](https://github.com/gnembon/fabric-carpet) (fabric-carpet) extension. There are some interesting features, The default settings will not have any impact on Minecraft.

  **I used the SettingManager function of MagicLib to manage rules. The usage method is basically the same as Carpet,Just replace```/carpet```with```/cca```. The rules are saved in```cca.conf```**

  **Operation command:```/cca```**



## Dependencies

| Dependency | Type     | Environment     | Link                                                                                               |
|------------|----------|-----------------|----------------------------------------------------------------------------------------------------|
| MagicLib   | Required | Client / Server | [Github](https://github.com/Hendrix-Shen/MagicLib) / [Modrinth](https://modrinth.com/mod/magiclib) |
| Carpet     | Required | Client / Server | [Github](https://github.com/gnembon/fabric-carpet) / [Modrinth](https://modrinth.com/mod/carpet)   |
| Fabric-API | Required | Client / Server | [Github](https://github.com/FabricMC/fabric) / [Modrinth](https://modrinth.com/mod/fabric-api)     |

## Rule List
### MagicBox
Control whether [CastSuppressor(CCESuppressor)](https://www.bilibili.com/read/cv24323749) is available

- Categories: ```CrystalCarpetAddition```
- Type: ```Boolean```
- Default value: Minecraft >= 1.20.2 ```false```  Minecraft < 1.20.2 ```true```
- Options: ```false``` ```true```
- Validators: Strict(Case-insensitive)

### CEnderPearlChunkLoading
The enderPearlChunkLoading function of [Carpet-Extra](https://github.com/gnembon/carpet-extra) is not working, it is an alternative

C has no special meaning, only for differentiation

**Carpet-Extra used my fix, so I removed it in 1.20.4 and above**

- Categories: ```CrystalCarpetAddition```
- Type: ```Boolean```
- Default value: ```false```
- Options: ```flase``` ```true```
- Validators: Strict(Case-insensitive)


### ItemShadowing
Use the ItemStack code in section 1.16.5, You can watch this video for its purpose.

[Click on me to jump to the video](https://youtu.be/mTeYwq7HaEA)

Note: I don't know how to translate this sentence. If you have any questions, please refer to the [Chinese README](https://github.com/Crystal0404/CrystalCarpetAddition) :(

- Categories: ```CrystalCarpetAddition```
- Type: ```Boolean```
- Default value: ```false```
- Options: ```flase``` ```true```
- Validators: Strict(Case-insensitive)


### CCAProtocol

Used to implement the mod blacklist, using the documentation to upload later

Version 1.20.4 and above will work fine

Version 1.19.4 has a bug working with Velocity etc, which is caused by fabric-api and I won't fix it for now.

- Categories: ```Network```
- Type: ```Boolean```
- Default value: ```false```
- Options: ```flase``` ```true```
- Validators: Strict(Case-insensitive)


## Supported versions
Please note that the version that is not written here is an unsupported version, 
and the issue of the unsupported version will be closed unconditionally, and it will be updated at any time!

- Minecraft 1.19.4
- Minecraft 1.20.4


## Other
  Use ```/magiclib language <language>``` to switch languages

  Set default language using ```/magiclib setDefault language <language>```

  Currently not supported below 1.19.4

  I'm using the Yarn mappings to de-obfuscate Minecraft and insert patches.

  Please report any issues to me in Chinese as much as possible

  For translation errors, please refer to the Chinese README for accuracy

   
  
