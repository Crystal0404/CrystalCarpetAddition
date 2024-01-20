## Crystal Carpet Additon

 > [!CAUTION]
 > CCA网络协议还在开发中, 目前可能导致通过velocity无法正常进入游戏,谨慎使用

  *中文* / [English](https://github.com/Crystal0404/CrystalCarpetAddition/blob/master/README_EN_US.md) 

  *经过数次跳票，终于做出来了!*

  这是一个Carpet的拓展, 包含一些有趣的功能, 默认所有规则设置均与Minecraft原版一致, 不会产生任何变化与影响

  **我使用了MagicLib的SettingManager管理规则, 使用方法与Carpet基本相同,你只需要用```/cca```代替```/carpet```即可, 规则保存在```cca.conf```中**

  **操作命令是```/cca``` 不是 ```/carpet```**

  **操作命令是```/cca``` 不是 ```/carpet```**

  **操作命令是```/cca``` 不是 ```/carpet```**


## 依赖库

| 依赖       | 类型 | 环境        | 链接                                                                                                 |
|----------|----|-----------|----------------------------------------------------------------------------------------------------|
| MagicLib | 必选 | 客户端 / 服务端 | [Github](https://github.com/Hendrix-Shen/MagicLib) / [Modrinth](https://modrinth.com/mod/magiclib) |
| Carpet   | 必选 | 客户端 / 服务端 | [Github](https://github.com/gnembon/fabric-carpet) / [Modrinth](https://modrinth.com/mod/carpet)   |

## 规则列表
### 魔法盒子(MagicBox)
控制是否可以使用[强转抑制器](https://www.bilibili.com/read/cv24323749)

- 分类: ```CrystalCarpetAddition```
- 类型: ```布尔值```
- 默认值: Minecraft >= 1.20.2 ```false```  Minecraft < 1.20.2 ```true```
- 参考值: ```false``` ```true```
- 验证器: 严格(不区分大小写)

### C珍珠加载(CEnderPearlChunkLoading)
移植并修复自[Carpet-Extra](https://github.com/gnembon/carpet-extra)的enderPearlChunkLoading

C没有特殊含义, 只为和Carpet-Extra的功能作区分

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


## 其他
  切换语言使用```/magiclib language <你的语言>```

  也可以使用```/magiclib setDefault language <你的语言>``` 来设置默认语言
  
  不会有Minecraft1.19.4以下的版本, 至少暂时是这样的
  
  使用了Yarn混淆映射表来反混淆Minecarft