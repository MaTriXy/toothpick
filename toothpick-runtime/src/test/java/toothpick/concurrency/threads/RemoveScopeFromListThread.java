/*
 * Copyright 2016 Stephane Nicolas
 * Copyright 2016 Daniel Molinero Reguerra
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package toothpick.concurrency.threads;

import java.util.List;
import toothpick.NodeUtil;
import toothpick.Scope;
import toothpick.Toothpick;

public class RemoveScopeFromListThread extends TestableThread {
  static final int ACCEPTANCE_THRESHOLD = 50;
  static int instanceNumber = 0;
  private List<Object> scopeNames;

  public RemoveScopeFromListThread(List<Object> scopeNames) {
    super("RemoveNodeThread " + instanceNumber++);
    this.scopeNames = scopeNames;
  }

  @Override
  public void doRun() {
    // pick a random node in the tree, starting from root
    // add a new child node to this node
    Scope scope = NodeUtil.findRandomNode(scopeNames, ACCEPTANCE_THRESHOLD);
    if (scope == null) {
      setIsSuccessful(true);
      return;
    }
    // remove any node except root
    if (scope.getParentScope() == null) {
      setIsSuccessful(true);
      return;
    }
    Object scopeName = scope.getName();
    Toothpick.closeScope(scopeName);
    setIsSuccessful(true);
  }
}
