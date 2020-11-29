/*
 * MIT License
 *
 * Copyright (c) 2020 Shiru ka
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package io.github.shiruka.api.conf;

import io.github.shiruka.api.conf.config.TestYamlConfig;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.IsTrue;

final class PathLoaderTest {

  @Test
  void load() {
    final var config = new TestYamlConfig();
    PathLoader.load(config);
    config.save();
    MatcherAssert.assertThat(
      "Couldn't set the config!",
      config.getTextTest().getConfig().isPresent(),
      new IsTrue());
    MatcherAssert.assertThat(
      "Couldn't set the config!",
      config.getFloatTest().getConfig().isPresent(),
      new IsTrue());
    MatcherAssert.assertThat(
      "Couldn't set the config!",
      config.getBoolTest().getConfig().isPresent(),
      new IsTrue());
    MatcherAssert.assertThat(
      "Couldn't set the config!",
      config.getIntTest().getConfig().isPresent(),
      new IsTrue());
    MatcherAssert.assertThat(
      "Couldn't set the config!",
      config.getLongTest().getConfig().isPresent(),
      new IsTrue());
    MatcherAssert.assertThat(
      "Couldn't set the config!",
      config.getDoubleTest().getConfig().isPresent(),
      new IsTrue());
  }
}