## Crystal Carpet Additon
    
  [中文](https://github.com/Crystal0404/CrystalCarpetAddition/blob/master/README.md) / *English*

  *I have finally completed it!*

A [Carpet mod](https://github.com/gnembon/fabric-carpet) (fabric-carpet) extension. There are some interesting features, The default settings will not have any impact on Minecraft.

  **I used the SettingManager function of MagicLib to manage rules. The usage method is basically the same as Carpet,Just replace```/carpet```with```/cca```. The rules are saved in```cca.conf```**

  **Operation command:```/cca```**



## Dependencies

| Dependency | Type     | Environment     | Link                                                                                               |
|------------|----------|-----------------|----------------------------------------------------------------------------------------------------|
| MagicLib   | Required | Client / Server | [Github](https://github.com/Hendrix-Shen/MagicLib) / [Modrinth](https://modrinth.com/mod/magiclib) |
| Carpet     | Required | Client / Server | [Github](https://github.com/gnembon/fabric-carpet) / [Modrinth](https://modrinth.com/mod/carpet)   |

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

- Categories: ```CrystalCarpetAddition```
- Type: ```Boolean```
- Default value: ```false```
- Options: ```flase``` ```true```
- Validators: Strict(Case-insensitive)


## Other
  Use ```/magiclib language <language>``` to switch languages

  Set default language using ```/magiclib setDefault language <language>```

  Currently not supported below 1.19.4

  I'm using the Yarn mappings to de-obfuscate Minecraft and insert patches.

  Please report any issues to me in Chinese as much as possible

  For translation errors, please refer to the Chinese README for accuracy

   
  