## Crystal Carpet Additon

**因为一些人对我个人以及这个mod的辱骂, 本模组无限期停止维护**

  *中文* / [English](https://github.com/Crystal0404/CrystalCarpetAddition/blob/master/README_EN_US.md) 

  *经过数次跳票，终于做出来了!*

  这是一个Carpet的拓展, 包含一些有趣的功能, 默认所有规则设置均与Minecraft原版一致, 不会产生任何变化与影响

  **我使用了MagicLib的SettingManager管理规则, 使用方法与Carpet基本相同,你只需要用```/cca```代替```/carpet```即可, 规则保存在```cca.conf```中**

  **操作命令是```/cca``` 不是 ```/carpet```**

  **操作命令是```/cca``` 不是 ```/carpet```**

  **操作命令是```/cca``` 不是 ```/carpet```**


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

如果Carpet-Extra的珍珠加载没有正常工作, 可以试试换用这个

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
