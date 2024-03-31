## Crystal Carpet Additon

  *中文* / [English](https://github.com/Crystal0404/CrystalCarpetAddition/blob/master/README_EN_US.md) 

  *经过数次跳票，终于做出来了!*

  这是一个Carpet的拓展, 包含一些有趣的功能, 默认所有规则设置均与Minecraft原版一致, 不会产生任何变化与影响

  **我使用了MagicLib的SettingManager管理规则, 使用方法与Carpet基本相同,你只需要用```/cca```代替```/carpet```即可, 规则保存在```cca.conf```中**

  **操作命令是```/cca``` 不是 ```/carpet```**

  **操作命令是```/cca``` 不是 ```/carpet```**

  **操作命令是```/cca``` 不是 ```/carpet```**

## 为什么我在游戏里找不到我想要的功能, 或者开启的功能不生效?

**请先检查规则列表是否有相关说明**

CrystalCarpetAdditon会自动禁用关闭和其它模组造成冲突的功能来保证其它没有冲突的功能可以正常工作.

如果这个功能依旧在规则列表中只是没有生效, 说明可以通过修改一些东西让它重新工作

如果它不存在规则列表中, 说明这个功能它完全无法在你的游戏中工作而被移除

详细信息请查阅CrystalCarpetAdditon的[Wiki](https://github.com/Crystal0404/CrystalCarpetAddition/wiki)

Wiki中有可以帮助你解决冲突的相关信息

## 依赖库

| 依赖         | 类型 | 环境        | 链接                                                                                                 |
|------------|----|-----------|----------------------------------------------------------------------------------------------------|
| MagicLib   | 必选 | 客户端 / 服务端 | [Github](https://github.com/Hendrix-Shen/MagicLib) / [Modrinth](https://modrinth.com/mod/magiclib) |
| Carpet     | 必选 | 客户端 / 服务端 | [Github](https://github.com/gnembon/fabric-carpet) / [Modrinth](https://modrinth.com/mod/carpet)   |
| Fabric-API | 必选 | 客户端 / 服务端 | [Github](https://github.com/FabricMC/fabric) / [Modrinth](https://modrinth.com/mod/fabric-api)     |


## 规则列表
### 魔法盒子(MagicBox)
控制是否可以使用[强转抑制器](https://www.bilibili.com/read/cv24323749)

是的, 它是CCA的第一个功能!

- 分类: ```CrystalCarpetAddition```
- 类型: ```布尔值```
- 默认值: Minecraft >= 1.20.2 ```false```  Minecraft < 1.20.2 ```true```
- 参考值: ```false``` ```true```
- 验证器: 严格(不区分大小写)

### C珍珠加载(CEnderPearlChunkLoading)
移植并修复自[Carpet-Extra](https://github.com/gnembon/carpet-extra)的enderPearlChunkLoading

C没有特殊含义, 只为和Carpet-Extra的功能作区分

**1.20.4的[Carpet-Extra](https://github.com/gnembon/carpet-extra)应用了我的修复, 为了防止冲突, 我在1.20.4及以后的版本中移除了它**

- 分类: ```CrystalCarpetAddition```
- 类型: ```布尔值```
- 默认值: ```false```
- 参考值: ```flase``` ```true```
- 验证器: 严格(不区分大小写)


### 物品分身(ItemShadowing)

重新引入1.16.5物品栏之间交换的逻辑, 使得[物品分身](https://www.bilibili.com/video/BV1cL4y1B75R)这项技术可以使用

注: 由于Minecraft项目结构的改变, 视频中代码演示仅作参考, CCA实际实现可能略有不同

- 分类: ```CrystalCarpetAddition```
- 类型: ```布尔值```
- 默认值: ```false```
- 参考值: ```flase``` ```true```
- 验证器: 严格(不区分大小写)


### CCA协议(CCAProtocol)

> [!Caution]
> CrystalCarpetAddition1.0.1生成的配置json与1.0.2的不兼容, 请删除后重新配置!

**请不要使用任何方式破坏这个功能, 我上传这个功能的代码目的是为了分享, 提供给有需要的朋友们, 重新创造一个不被投影打印机等客户端mod破坏的Minecraft**

用于实现模组黑名单  [使用文档](https://github.com/Crystal0404/CrystalCarpetAddition/blob/master/doc/CCAProtocol.md)

它完美支持1.20.4及以上版本(代码理论上支持1.20.2和1.20.3)

它在1.19.4原版情况下可以正常工作, 但是与Velocity等一起工作时会出现问题, 这个是由Fabric-API引起的问题, 我暂时不会去修复它. 

- 分类: ```网络```
- 类型: ```布尔值```
- 默认值: ```false```
- 参考值: ```flase``` ```true```
- 验证器: 严格(不区分大小写)


### 比较器可"浮空"放置(ComparatorCanPlaceAboveAir)

*注: 只有Minecraft1.20.4以上有这个功能*

把比较器掉落为物品的行为修改到与1.20.1一致(打开活版门不会让比较器掉落为物品)

- 分类: ```CrystalCarpetAddition```
- 类型: ```布尔值```
- 默认值: ```false```
- 参考值: ```flase``` ```true```
- 验证器: 严格(不区分大小写)


### 禁用蝙蝠生成(NoBatSpawn)

让你的世界不再生成烦人的蝙蝠.

- 分类: ```CrystalCarpetAddition```
- 类型: ```布尔值```
- 默认值: ```false```
- 参考值: ```flase``` ```true```
- 验证器: 严格(不区分大小写)


### 末影人不能在下界拾取方块(EndermanCannotPickUpBlocksInNether)

保护你的下界地形不被末影人破坏, 并且防止大量末影人因拾取方块不消失从而造成卡顿.

- 分类: ```CrystalCarpetAddition```
- 类型: ```布尔值```
- 默认值: ```false```
- 参考值: ```flase``` ```true```
- 验证器: 严格(不区分大小写)


### 潜影盒红石信号输出拓展ShulkerBoxPowerOutputExpansion)

修改比较器检测潜影盒输出信号强度的逻辑

占用物品栏的数量就是输出信号的强度

- 分类: ```创造工具```
- 类型: ```布尔值```
- 默认值: ```false```
- 参考值: ```flase``` ```true```
- 验证器: 严格(不区分大小写)


### 潜影盒红石信号输出拓展颜色设置(ShulkerBoxPowerOutputExpansionColour)

设置潜影盒红石信号输出拓展生效的潜影盒颜色(默认粉色).

- 分类: ```创造工具```
- 类型: ```枚举```
- 默认值: ```pink```
- 参考值: 略
- 验证器: 严格(不区分大小写)


### 保留聊天信息(KeepMessage)

*注: 只有Minecraft1.20.4以上有这个功能*

使用```/server```切换服务器时, 不会丢失聊天信息.

- 分类: ```CrystalCarpetAddition```
- 类型: ```布尔值```
- 默认值: ```false```
- 参考值: ```flase``` ```true```
- 验证器: 严格(不区分大小写)

## 支持版本
请注意, 没有写在这里的版本为不受支持版本, 不受支持版本的issues会无条件直接关闭, 它会随时更新!

- Minecraft 1.19.4
- Minecraft 1.20.1
- Minecraft 1.20.4


## 其他
  切换语言使用```/magiclib language <你的语言>```

  也可以使用```/magiclib setDefault language <你的语言>``` 来设置默认语言
  
  不会有Minecraft1.19.4以下的版本, 至少暂时是这样的
  
  使用了Yarn混淆映射表来反混淆Minecarft
