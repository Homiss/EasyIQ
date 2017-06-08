package cn.wcode.core;

import cn.wcode.model.ReciteRecord;

/**
 * 记忆曲线
 */
public class Ebbinghaus {
	public static final int FORGETTIN_CURVE[] = {
			5 * 60 * 1000,			// 5分钟
			30 * 60 * 1000,			// 30分钟
			12*60 * 60 * 1000,		// 12小时
			1*24*60 * 60 * 1000,	// 1天
			2*24*60 * 60 * 1000,	// 2天
			4*24*60 * 60 * 1000,	// 4天
			7*24*60 * 60 * 1000,	// 7天
			15*24*60 * 60 * 1000	// 15天
	};

}