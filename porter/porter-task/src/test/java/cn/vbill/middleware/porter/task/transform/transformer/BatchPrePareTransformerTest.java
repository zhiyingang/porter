/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhangkewei[zhang_kw@suixingpay.com]
 * @date: 2018年01月09日 17:52
 * @Copyright ©2018 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */

package cn.vbill.middleware.porter.task.transform.transformer;

import cn.vbill.middleware.porter.core.event.s.EventType;
import cn.vbill.middleware.porter.common.consumer.Position;
import cn.vbill.middleware.porter.core.event.etl.ETLBucket;
import cn.vbill.middleware.porter.core.event.etl.ETLRow;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhangkewei[zhang_kw@suixingpay.com]
 * @date: 2018年01月09日 17:52
 * @version: V1.0
 * @review: zhangkewei[zhang_kw@suixingpay.com]/2018年01月09日 17:52
 */
@RunWith(JUnit4.class)
public class BatchPrePareTransformerTest {

    @Test
    public void transform() {
        List<ETLRow> rows = new ArrayList<>();
        rows.add(new ETLRow(0, 0, "s", "t", EventType.DELETE, null, null, null));
        rows.add(new ETLRow(0, 0, "s", "t", EventType.DELETE, null, null, null));
        rows.add(new ETLRow(0, 0, "s", "t", EventType.INSERT, null, null, null));
        rows.add(new ETLRow(0, 0, "s", "t", EventType.DELETE, null, null, null));
        rows.add(new ETLRow(0, 0, "s", "t", EventType.INSERT, null, null, null));
        rows.add(new ETLRow(0, 0, "s", "t", EventType.INSERT, null, null, null));
        rows.add(new ETLRow(0, 0, "s", "t", EventType.INSERT, null, null, null));
        rows.add(new ETLRow(0, 0, "s", "t", EventType.TRUNCATE, null, null, null));
        rows.add(new ETLRow(0, 0, "s", "t", EventType.TRUNCATE, null, null, null));
        rows.add(new ETLRow(0, 0, "s", "t", EventType.UPDATE, null, null, null));
        rows.add(new ETLRow(0, 0, "s", "t", EventType.TRUNCATE, null, null, null));
        rows.add(new ETLRow(0, 0, "s", "t", EventType.INSERT, null, null, null));
        rows.add(new ETLRow(0, 0, "s", "t", EventType.INSERT, null, null, null));

        ETLBucket bucket = new ETLBucket("0", rows, new Position() {
            @Override
            public boolean checksum() {
                return true;
            }
        });

        BatchPrePareTransformer t = new BatchPrePareTransformer();
        t.transform(bucket, null);

        Assert.assertEquals(bucket.getBatchRows().size(), 8);
    }
}
