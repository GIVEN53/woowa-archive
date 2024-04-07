1. 로컬 **child repository**에서 새로운 branch 생성 (`step2` base)

```bash
git switch -c {branch_name}
```

2. `step1` squash merge 해제 후 push

```bash
git rebase step1
```

3. 로컬 **parent repository**에서 subtree에 child repository 병합 후 push

```bash
git subtree add --prefix={path/directory_name} {child_repository_remote_git_url} {branch_name} -m {message}
```
