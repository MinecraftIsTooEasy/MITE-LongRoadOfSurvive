package net.oilcake.mitelros.item;

import net.minecraft.*;

public class ItemGuideBook extends ItemWrittenBook {

    public ItemGuideBook(int par1) {
        super(par1);
    }
    @Override
    public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
        return super.onItemRightClick(player, partial_tick, ctrl_is_down);
    }
    public static NBTTagCompound generateBookContents() {
        NBTTagCompound nbt = new NBTTagCompound();
        NBTTagList pages = new NBTTagList();
        nbt.setTag("pages", pages);
        nbt.setTag("author", new NBTTagString("author", "Schwartz"));
        nbt.setTag("title", new NBTTagString("title", "* GUIDE *"));
        pages.appendTag(new NBTTagString("1", "* GUIDE *\n该指南的扉页部分只负责解决游戏内技术性问题，如有需要了解部分更新内容请跳转05页。\n\n注意：我们不会提供一些机制对应的所有解决方案！"));
        pages.appendTag(new NBTTagString("2", "有关渲染问题：请再三确认游戏使用的显卡：Intel核心显卡需要回滚老版本驱动(实际版本未测试)，如果需要通过更换其他显卡解决渲染问题，可以参考后页的步骤。"));
        pages.appendTag(new NBTTagString("3", "NVIDIA显卡只需正常更新驱动，在桌面右键菜单可以找到NVIDIA控制面板，在「管理3D设置」项内选择「首选图形处理器」为「高性能」。AMD显卡在近期的驱动更新中可能导致内存泄露，如有需要可前往官网回滚更新。"));
        pages.appendTag(new NBTTagString("4", "再三重申：模组内自带的坐标器和时间显示需要拿取特定物品如时钟、指南针以显示。"));
        pages.appendTag(new NBTTagString("5", "*有关水分 7-12页\n*有关熔炉 13页\n*有关矿物规划 14-15页\n*有关金苹果 16页\n*有关新金属 17-19页"));
        pages.appendTag(new NBTTagString("6", "*有关新生物群系 20-21页\n*有关额外机制 22-26页\n*有关新物品 29-32页\n*附页 33页+"));
        pages.appendTag(new NBTTagString("7", "在游玩模组时，作者默认「玩家」(下称「你」)已经游玩过MITE R196并拥有一定的理论知识，同时一部分已有的无关痛痒的机制碍于篇幅不再赘述。"));
        pages.appendTag(new NBTTagString("8", "位于玩家状态栏右下角饱食度上方的即模组新添加的水分条，依然会随时间推移减少，在玩家食用特定食物时将直接减少或者增加。"));
        pages.appendTag(new NBTTagString("9", "在食用肉类时这个条会立即减少，在它为空时玩家将逐渐减少血量直到死亡；留意一些新的花和仙人掌，在合成后将触发一个新的成就：紧急避险。对应的合成产物可以在你做出碗之前恢复含水量。"));
        pages.appendTag(new NBTTagString("10", "如果对水分拥有迫切的需求，你可以在水中按住潜行键并保持一段时间，这也会让你恢复一部分水分。\n\n至此，你可以正常前进到燧石工具时代了。"));
        pages.appendTag(new NBTTagString("11", "在做出碗舀取水分乃至你在水中潜行获得debuff时，你可能会留意到多数地区的水饮用之后会出现问题，你可以使用黏土炉去蒸馏「一碗可疑的水」或者「一碗沼泽水」来让水变得安全(务必参考p31)。"));
        pages.appendTag(new NBTTagString("12", "一些河流部分段落的水可能无需烧炼即可饮用而不会获取debuff，这也在水质的考虑范围之内。同时你需要干净的水来制作相关的碗类食物，牛奶也不失为一种补水的选择。"));
        pages.appendTag(new NBTTagString("13", "MITE-ITF的熔炉需要使用打火石右键启动，一些看起来明显带火的玩意(除了火把)也可用来作为火引子；一些物品可能不耐高温；在熔炉熄灭之前你不需要再次点火；\n\n直接用火烧掉落状态的肉不失为一种选择。"));
        pages.appendTag(new NBTTagString("14", "在玩家做出第一把铜镐挖掘矿物时，你可能会惊讶于矿物的掉落，保持淡定。按照R196的第一把镐的规划先石头再煤炭最后铜矿即可，在熔炉中将之冶炼成金属粒并合成，保持你的耐心。"));
        pages.appendTag(new NBTTagString("15", "一块煤炭可以烧炼32个矿物碎块，不必担心你需要挖大量的煤矿用于冶炼矿物；\n此外，在没有足够耐高温的金属桶前使用岩浆烧炼矿物时极其冒险的行为。"));
        pages.appendTag(new NBTTagString("16", "金苹果在MITE-ITF的强度被大幅度削弱，基本没有办法在强大怪物面前救你一命，它在MITF的定位更类似于酿造材料，请参考MITE-ITF合成表内的实验药水，亦或是将它丢进酿造台。"));
        pages.appendTag(new NBTTagString("17", "* 镍 *\n这种矿石的完整盔甲可以免疫史莱姆的攻击，即便它严重损毁也可以一定程度上减免史莱姆的伤害，同时可以在没有附魔的情况下攻击黑色和灰色史莱姆，这让一些简单附魔成为非必需品。"));
        pages.appendTag(new NBTTagString("18", "* 钨 *\n用于强化地狱怪物的金属，拥有优秀的耐久性能，在制作艾德曼合金工作台时需要其作为前置，纯钨制品同样免疫岩浆的损毁。"));
        pages.appendTag(new NBTTagString("19", "* 乌鲁金属 *\n位于末地的附魔特化金属，可以拥有更多的附魔词条同时拥有一个区别于其他工具的合成路线，碍于作者的更新速度和方块id问题它并没有完全的金属制品。"));
        pages.appendTag(new NBTTagString("20", "* 风袭高原 *\n拥有极其庞大且圆石覆盖的覆雪山体，同时会生成露天的绿宝石，在该群系的地下会生成极其发达的矿脉网络，相当危险。"));
        pages.appendTag(new NBTTagString("21", "* 萨瓦纳草原 *\n与沙漠相同的不会下雨拥有兰花和少量树的草原，是最为理想的常驻生物群系。"));
        pages.appendTag(new NBTTagString("22", "* 寒冷惩罚 *\n在覆雪生物群系露天呆一段时间后会获取不断刷新的缓慢与挖掘疲劳，并在其达到高等级时将冻死玩家，穿着对应的盔甲可以减免这个效果，同时极地护甲可以免疫这个效果。"));
        pages.appendTag(new NBTTagString("23", "* 季节机制*\n每个蓝月循环内会出现四季交替，春季不会有任何影响；夏季会延长下雨的事件；秋季的水果掉落率会增加；而冬季会让所有非热带群系变为覆雪寒冷群系。"));
        pages.appendTag(new NBTTagString("24", "* 拓展营养条*\n在左下角的营养条消耗完毕后将进入营养不良状态并继续减少隐藏营养条，过久的营养不良会导致更加严重的惩罚(如停止回血乃至缓慢扣血死亡)。"));
        pages.appendTag(new NBTTagString("25", "* 绿宝石 *\n绿宝石将只生成于覆雪群系，请在进入这些群系时准备好御寒措施，也可以考虑制作一些酒来暂时压制寒冷惩罚的发作并用/tpt指令关注你的体温。"));
        pages.appendTag(new NBTTagString("26", "* 宝石机制 *\n请使用钻石，古金属，黑曜石制作「附魔回流底座」来提取宝石的经验，GUI上侧可放入宝石转化经验，并在下方用水瓶或金属粒提取。"));
        pages.appendTag(new NBTTagString("27", "* 附魔强化 *\n使用古金属，附魔瓶，黑曜石制作的「附魔增幅底座」可以放置在附魔台下方用于强化附魔台来获取更强大的附魔效能和更高昂的经验费用。在乌鲁金属上尤为有益。"));
        pages.appendTag(new NBTTagString("28", "* 高版本移植 *\n添加了一些自高版本中移植的怪物，物品并予以MITE拓展(包括MITE本体拓展出的怪物)，这些新添加的物品将不再于之后的文本中赘述。"));
        pages.appendTag(new NBTTagString("29", "* 法杖 *\n自遗迹宝箱和部分骷髅手中获得的远程武器，拥有平直弹道并可以对怪物施加一些特殊的debuff。"));
        pages.appendTag(new NBTTagString("30", "* 钉锤 *\n对护甲和骷髅强化的武器，在攻击目标时额外无视目标3点护甲且对骷髅拥有100%的增伤，类似战锤"));
        pages.appendTag(new NBTTagString("31", "* 陶瓦碗 *\n单个黏土可以合成黏土碗并在熔炉中4个为一个单位烧制成陶瓦碗，拥有碗类相同的功效并可以烧水；\n\n木碗无法烧水！"));
        pages.appendTag(new NBTTagString("32", "* 图腾 *\n移植自高版本的不死图腾，每种图腾都拥有不同的效果，但是需要手持才能生效(在受到死亡攻击时消失并产生效果)！"));
        pages.appendTag(new NBTTagString("33", "* 配置文件 *\n详见「StuckChallenge.cfg」和「ExperimentalOption.cfg」，按照正常流程更改其中的配置可以让玩家体验到更加不一样的MITE-ITF。"));
        pages.appendTag(new NBTTagString("34", "更多改动详见更新日志！ 链接：https://docs.qq.com/doc/p/6556873ce3bc40e32e1b61a0d05e8dc461792230"));
        nbt.setString("flavor_text", "Try to survive in distorted MITE World.");
        return nbt;
    }
}
