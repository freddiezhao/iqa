package taobao.ruoye.test_objects;

import taobao.core.base.extensions.TestBaseExt;
import taobao.ruoye.test_objects.data_providers.RuoyeDataProviders;

/**
 * Class contains test methods for Index page
 * 
 */
public class RuoyeTests extends TestBaseExt
{
	public RuoyeDataProviders ruoyeData = new RuoyeDataProviders();

	public void ruoyeTest()
	{
		wd().openURL("http://mybrand.tmall.com/brandInfo.htm?spm=a220o.1000855.w5001-3915415375.2.xnu0J2&brandId=286680193&scm=1048.1.1.5&scene=taobao_shop");

	}
}
