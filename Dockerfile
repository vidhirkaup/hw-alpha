FROM public.ecr.aws/amazonlinux/amazonlinux:latest
#Copy the build's output binary from the previous build container
#COPY --from=build /bin/HelloWorld /bin/HelloWorld
ENTRYPOINT ["/bin/HelloWorld"]
