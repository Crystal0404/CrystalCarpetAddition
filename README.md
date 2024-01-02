## Crystal Carpet Additon

  *经过数次跳票，终于做出来了!*

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


## 其他
  切换语言使用```/magiclib language <你的语言>```

  也可以使用```/magiclib setDefault language <你的语言>``` 来设置默认语言
  
  不会有1.19.4一下的版本, 至少暂时是这样的