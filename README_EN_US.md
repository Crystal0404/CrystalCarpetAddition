## Crystal Carpet Additon

    
  [中文](https://github.com/Crystal0404/CrystalCarpetAddition) / *English*

  *I have finally completed it!*

A [Carpet mod](https://github.com/gnembon/fabric-carpet) (fabric-carpet) extension. There are some interesting features, The default settings will not have any impact on Minecraft.

  **The usage method is basically the same as Carpet,Just replace```/carpet```with```/cca```. The rules are saved in```cca.conf```**

  **Operation command:```/cca```**

## A message for our English-speaking friends

You may have found that the English instructions here are not very good, including the documentation because English is not my everyday language, if you want to make a better translation of this mod, please feel free to submit a PR to me : )

For some reason, the only way you can reach me is on Github, so if you have any questions, you can just open an issue!

## Why can't I find the feature I want in the game, or the feature I turned on doesn't work?

**Check the list of rules for descriptions**

CrystalCarpetAdditon will automatically disable the ability to disable conflicts with other mods to ensure that other non-conflicting features work properly.

If the feature is still in the list of rules but doesn't work, you can change something to make it work again

If it's not in your rules list, the feature won't work in your game, so it's disabled and hidden

For more information, see Crystal Carpet Additon's [Wiki](https://github.com/Crystal0404/CrystalCarpetAddition/wiki)

The wiki has information that can help you resolve conflicts

## Dependencies

| Dependency | Type     | Environment     | Link                                                                                             |
|------------|----------|-----------------|--------------------------------------------------------------------------------------------------|
| Carpet     | Required | Client / Server | [Github](https://github.com/gnembon/fabric-carpet) / [Modrinth](https://modrinth.com/mod/carpet) |
| Fabric-API | Optional | Client / Server | [Github](https://github.com/FabricMC/fabric) / [Modrinth](https://modrinth.com/mod/fabric-api)   |

## Rule List
### MagicBox
Control whether [CastSuppressor(CCESuppressor)](https://www.bilibili.com/read/cv24323749) is available

- Categories: ```MC Tweaks```
- Type: ```Boolean```
- Default value: Minecraft >= 1.20.2 ```false```  Minecraft < 1.20.2 ```true```
- Options: ```false``` ```true```
- Validators: Strict(Case-insensitive)

### CEnderPearlChunkLoading
The enderPearlChunkLoading function of [Carpet-Extra](https://github.com/gnembon/carpet-extra) is not working, it is an alternative

C has no special meaning, only for differentiation

**Carpet-Extra used my fix, so I removed it in 1.20.4 and above**

- Categories: ```MC Tweaks```
- Type: ```Boolean```
- Default value: ```false```
- Options: ```flase``` ```true```
- Validators: Strict(Case-insensitive)


### ItemShadowing
Use the ItemStack code in section 1.16.5, You can watch this video for its purpose.

[Click on me to jump to the video](https://youtu.be/mTeYwq7HaEA)

Note: I don't know how to translate this sentence. If you have any questions, please refer to the [Chinese README](https://github.com/Crystal0404/CrystalCarpetAddition) :(

- Categories: ```MC Tweaks```
- Type: ```Boolean```
- Default value: ```false```
- Options: ```flase``` ```true```
- Validators: Strict(Case-insensitive)


### CCANetworkProtocol

**Please don't break this feature in any way, I uploaded the code for this feature to share, to provide it to friends in need, to recreate a Minecraft that is not broken by client mods such as Litematica-printer**

Used to get client mod information and some other features (Mod blacklist), it has an interface for other mods to use. [Use documentation](https://github.com/Crystal0404/CrystalCarpetAddition/wiki/CCANetworkProtocol)

Version 1.20.4 and above will work fine

Version 1.19.4 has a bug working with Velocity etc, which is caused by fabric-api and I won't fix it for now.

**If something goes wrong, try deleting the profile and restarting**

- Categories: ```Network```
- Type: ```Boolean```
- Default value: ```false```
- Options: ```flase``` ```true```
- Validators: Strict(Case-insensitive)

### ComparatorCanPlaceAboveAir

Changed the code for comparators to drop items to match version 1.20.1.(Opening a trapdoor will not cause the comparator to drop as an item)

- Categories: ```MC Tweaks```
- Type: ```Boolean```
- Default value: ```false```
- Options: ```flase``` ```true```
- Validators: Strict(Case-insensitive)


### NoBatSpawn

Makes bats no longer spawn.

- Categories: ```MC Tweaks```
- Type: ```Boolean```
- Default value: ```false```
- Options: ```flase``` ```true```
- Validators: Strict(Case-insensitive)


### EndermanCannotPickUpBlocksInNether

Protect your nether terrain from being destroyed by Enderman and prevent a large number of Endermen from not despawning after picking up blocks.

- Categories: ```MC Tweaks```
- Type: ```Boolean```
- Default value: ```false```
- Options: ```flase``` ```true```
- Validators: Strict(Case-insensitive)


### ShulkerBoxPowerOutputExpansion

Modify the output logic of the comparator detection ShulkerBox

The number of inventory occupied is the number output!

- Categories: ```CreativeTools```
- Type: ```Boolean```
- Default value: ```false```
- Options: ```flase``` ```true```
- Validators: Strict(Case-insensitive)


### ShulkerBoxPowerOutputExpansionColour

Specifies the color of the ShulkerBox in effect for the Shulker Box Power Output Expansion(Pink by default).

- Categories: ```CreativeTools```
- Type: ```enum```
- Default value: ```pink```
- Options: It's too much, I'm too lazy to write XD
- Validators: Strict(Case-insensitive)

## Supported versions
Please note that the version that is not written here is an unsupported version, 
and the issue of the unsupported version will be closed unconditionally, and it will be updated at any time!

- Minecraft 1.19.4
- Minecraft 1.20.1
- Minecraft 1.20.4


## Other

  Currently not supported below 1.19.4

  I'm using the Yarn mappings to de-obfuscate Minecraft and insert patches.

  Please report any issues to me in Chinese as much as possible

  For translation errors, please refer to the Chinese README for accuracy

