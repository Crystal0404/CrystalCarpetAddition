## Crystal Carpet Additon
[![GitHub License](https://img.shields.io/github/license/Crystal0404/CrystalCarpetAddition)]()
[![Modrinth Downloads](https://img.shields.io/modrinth/dt/G26sLP13?logo=Modrinth&label=Modrinth)](https://modrinth.com/mod/crystalcarpetaddition)
[![GitHub Issues or Pull Requests](https://img.shields.io/github/issues/Crystal0404/CrystalCarpetAddition?color=blue)]()

  *中文* / [English](https://github.com/Crystal0404/CrystalCarpetAddition/blob/master/README_EN_US.md) 

  > [!NOTE]
  > 
  > CCA已进入维护模式. 除了更新至最新的Minecraft版本外, 仅修复Bug, 不添加新功能.

  *经过数次跳票，终于做出来了!*

  这是一个Carpet的拓展, 包含一些有趣的功能, 默认所有规则设置均与Minecraft原版一致, 不会产生任何变化与影响

  **我使用了自己的SettingManager管理规则, 使用方法与Carpet基本相同,你只需要用```/cca```代替```/carpet```即可, 规则保存在```cca.conf```中**

  **操作命令是```/cca``` 不是 ```/carpet```**

  **规则列表等更多信息请查阅[CCA文档](https://crystal0404.github.io/cca-doc)**

## 依赖库

| 依赖         | 类型 | 环境        | 链接                                                                                                 |
|------------|----|-----------|----------------------------------------------------------------------------------------------------|
| Carpet     | 必选 | 客户端 / 服务端 | [Github](https://github.com/gnembon/fabric-carpet) / [Modrinth](https://modrinth.com/mod/carpet)   |

**注: v1.10.0以前CCANetworkProtocol规则通过`fabric-api`进行网络通信, 因此在小于1.10.0的版本中需要安装`fabric-api`, 此规则于v1.10.0中移除, 因此在大于v1.10.0版本中不再需要`fabric-api`**

*[点我前往fabric-api的Modrinth下载页面](https://modrinth.com/mod/fabric-api)*

## 支持版本
不受支持版本的issues会无条件直接关闭, 它会随时更新!

详细请查阅[EOL](https://crystal0404.github.io/cca-doc/eol/)

## 其他
  
  不会有Minecraft1.19.4以下的版本, 至少暂时是这样的
  
  使用了Yarn混淆映射表来反混淆Minecarft
