# CCAProtocol

**Sorry for the English friends, this document is only in Chinese, you can use ChatGpt to help understand**

## 介绍
在玩家加入游戏时, 会检查玩家的模组列表中是否含有服务器不允许使用的模组, 安装不允许使用模组的玩家无法进入游戏. 如果服务器开启了这个功能, 所有玩家都必须安装CrystalCarpetAddition,否则将无法进入游戏.

## 服务端配置
在你的Fabric模组配置文件夹```config```中找到这个文件```CrystalCarpetAddition\CrystalCarpetAddition.json```, 默认情况下它是这样的.(如果不存在, 请先启动一次服务器)
```json
{
  "black_list": []
}
```
在black_list中的列表填写你需要禁用的mod_id, 下面是正确填写的一个例子
```json
{
  "black_list": [
    "mod1",
    "mod2"
  ]
}
```

## 如何获取mod_id

~~最简单的办法是询问模组作者~~

下载你需要禁用的模组文件, 一般你的压缩软件可以直接解压它, 如果不能解压, 可以把后缀```.jar```换成```.zip```然后解压.

里面会有一个叫```fabric.mod.json```的文件, 里面有一项大概是这样的```"id": "xxx"```, 在这个例子中```xxx```就是要寻找的mod_id, 把他按上面演示的格式填入配置文件,重新启动服务器就被应用了.

## MagicLib系列的mod

这类mod比较特殊, 因为它使用了特殊的包装器(CCA也是MagicLib系列的Mod), 你不仅需要填写```-all```版本的mod_id, 你还需要填写它单独版本的模组id, 它单独版本往往发布在github仓库中.如果很不幸作者没有发布单独版本的mod, 这里的建议是观察CCA
```-all```版本的mod_id与CCA单独版本的mod_id, 我相信你可以发现规律的XD

## CCAProtocol在CrystalCarpetAddition1.0.2中的更改

建议删除旧的配置文件, 它与新版配置文件不兼容

你可能注意到我在1.0.2中新增了一点东西, 现在它可以自定义拦截规则了!, 现在它的默认配置文件长这样
```json
{
  "BlackList": [],
  "GetModList": false,
  "Regex": false
}
```
```GetModList```开启这个选项, 会将进入游戏的玩家的模组列表打印到控制台以及日志中

```Regex```开启这个选项后, 拦截规则将使用正则表达式模式, 你可以在```BlackList```中写入正则表达式, 所有正则表达式都会被应用.

**注意: 正则表达式匹配的是mod加载的完整名字, 即你开启```GetModList```后返回到控制台上的名字, 不仅仅只有mod的id, 还有mod版本, 请谨慎编写**

## 开启CCAProtocol

与其他CCA的设置相同, 你需要手动开启CCAProtocol, 它默认的关闭的

如果你要使用它, 我十分建议你将它设置成默认开启

记得CCA的操作指令是```/cca```, 你可以在```网络```或者```Network```分类中找到它

## 下面是Crystal0404的碎碎念
这个功能的开发其实遇上了很多困难, 感谢Fabric团队的开发者们的帮助, 我发现网络API的故障到他们修复, 他们甚至用了不到24h.

开发这个功能的原因是我自己不喜欢```Litematica-printer```, 它太疯狂了, 它破坏了我喜欢的MC, 即使我所在的服务器明令禁止使用
它, 但并不是所有人都是会遵守这条规则, CCA开发的目的就是让我喜欢的MC不受外力的破坏.

事实上, 如果你了解一点模组开发, 破坏CCAProtocol并不困难, 但我并不希望任何人去破坏它.请尊重服务器中大部分人的意愿. 

最后, 我把它(CrystalCarpetAddition)分享给你, 如果它可以帮到你, 我会非常的开心.