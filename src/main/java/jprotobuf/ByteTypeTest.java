/*
 * Copyright 2002-2007 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jprotobuf;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;

/**
 * Test all single field class encode and decode
 * 
 * @author xiemalin
 * @since 1.0.9
 */
public class ByteTypeTest {

    @Test /*** 测试字节数组的序列化和反序列化*/
    public void testTypeClass1() throws IOException  {
        Codec<ByteTypeClass1> codec = ProtobufProxy.create(ByteTypeClass1.class);
        ByteTypeClass1 o = new ByteTypeClass1();
        o.setBytes("hello".getBytes());
        byte[] bb = codec.encode(o);
        ByteTypeClass1 class1 = codec.decode(bb);
        System.out.println(new String(class1.getBytes()));
    }
    
    @Test /*** 测试字节数组的序列化和反序列化*/
    public void testTypeClass4() throws IOException  {
        Codec<ByteTypeClass4> codec = ProtobufProxy.create(ByteTypeClass4.class);
        ByteTypeClass4 o = new ByteTypeClass4();
        o.bytes3 = new Byte[]{1,2,127,0};
        byte[] bb = codec.encode(o); // 经过序列化之后，Byte[] bytes3 的值有，但是反序列化之后，其值丢失了
        ByteTypeClass4 class1 = codec.decode(bb);// 反序列号之后Byte[] bytes3 的值丢失了。
        Assert.assertArrayEquals(o.bytes3,class1.bytes3);
    }
    
    @Test /*** 测试字节数组的序列化和反序列化*/
    public void testTypeClass2() throws IOException  {
        Codec<ByteTypeClass2> codec = ProtobufProxy.create(ByteTypeClass2.class);
        
        ByteTypeClass2 o = new ByteTypeClass2();
        o.bytes = new byte[] {1, 2};
        byte[] bb = codec.encode(o);

        ByteTypeClass2 o2 = new ByteTypeClass2();
        o2.bytes = new  byte[]{1,2,3};
        
        ByteTypeClass2 class1 = codec.decode(bb);
        Assert.assertArrayEquals(o.bytes, class1.bytes);
//        Assert.assertArrayEquals(o2.bytes, class1.bytes);
    }
    
    @Test /*** 测试字节数组的序列化和反序列化*/
    public void testTypeClass3() throws IOException  {
        Codec<ByteTypeClass3> codec = ProtobufProxy.create(ByteTypeClass3.class);
        
        ByteTypeClass3 o = new ByteTypeClass3();
        o.setBytes(new byte[] {1, 2});
        byte[] bb = codec.encode(o);
        
        ByteTypeClass3 class1 = codec.decode(bb);
        Assert.assertArrayEquals(o.getBytes(), class1.getBytes());
    }
    
}
