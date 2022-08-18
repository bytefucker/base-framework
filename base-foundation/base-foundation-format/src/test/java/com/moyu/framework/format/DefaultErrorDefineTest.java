package com.moyu.framework.format;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * DefaultErrorDefineTest
 *
 * @author yihongzhi
 * @date 2022/8/17
 */
public class DefaultErrorDefineTest {

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void testError() {
    throw DefaultErrorDefine.INSTANCE_ERROR.exception();
  }
}